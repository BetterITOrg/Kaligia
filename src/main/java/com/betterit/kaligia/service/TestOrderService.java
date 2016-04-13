/**
 * 
 */
package com.betterit.kaligia.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.dao.model.kaligia.RunOrder;
import com.betterit.kaligia.dao.model.kaligia.Specimen;
import com.betterit.kaligia.dao.model.kaligia.SpecimenExample;
import com.betterit.kaligia.dao.model.kaligia.Subject;
import com.betterit.kaligia.dao.model.kaligia.SubjectExample;
import com.betterit.kaligia.dao.model.kaligia.TestOrder;
import com.betterit.kaligia.dao.repository.kaligia.RunOrderMapper;
import com.betterit.kaligia.dao.repository.kaligia.SpecimenMapper;
import com.betterit.kaligia.dao.repository.kaligia.SubjectMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestOrderMapper;

/**
 * @author V135012
 *
 */

@Service
public class TestOrderService {
	
	private static final Logger log = LoggerFactory.getLogger(TestOrderService.class);
	
	@Autowired
	private TestOrderMapper tom;
	
	@Autowired
	private RunOrderMapper rom;
	
	@Autowired
	private UsersService usm;
	
	@Autowired
	private SubjectMapper sm;
		
	@Autowired
	private SpecimenMapper spm;
	
	@Autowired
	private EndPointService eps;
	
	
	public TestOrder createTestOrder(
			String orderNo,
			String description,
			String patientID,
			String dateOfBirth,
			String patientEthnicity,
			String patientGender
			) {
		
		TestOrder tord = new TestOrder();
		
		tord.setOrderNo(orderNo);
		if(description.length() > 64)
			tord.setDescription(description.substring(0, 63));
		else 
			tord.setDescription(description);
		tord.setCreationDate(new Date());
		tord.setCreatedBy(usm.getUserByName("").getUserId());
		tord.setSiteId(1); // ToDo: Hard Coded to 1
		tord.setSubjectId(getSubject(
				patientID,
				dateOfBirth,
				patientEthnicity,
				patientGender
				).getSubjectId());
		int rc = tom.insert(tord);
		if(rc != 1) {
			log.info("Failed to insert test order.");
		}
		
		return tord;
		
	}

	public Subject getSubject(
							String patientID,
							String dateOfBirth,
							String patientEthnicity,
							String patientGender
							) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Subject sub = new Subject();
		SubjectExample subE = new SubjectExample();
		subE.createCriteria().andNameEqualTo(patientID);
		List<Subject> sl = sm.selectByExample(subE);
		if(sl.size() == 0) {
			sub.setName(patientID);
			try {
				sub.setDob(formatter.parse(dateOfBirth));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				sub.setDob(null); //Set default date
			}
			sub.setEthnicity(patientEthnicity);
			sub.setGender(patientGender);
			sub.setCreationDate(new Date());
			sub.setCreatedBy(usm.getUserByName("").getUserId());
			int rc = sm.insert(sub);
			if (rc == 1) {
				log.info("Inserting Subject : " + patientID);
			} else {
				log.info("Failed to insert Subject : " + patientID);
			}
			
		} else {
			sub = sl.get(0);
			log.info("Found Subject : " + sl.get(0).getName());
		}
		
		return sub;
	}
	
	public Specimen getSpecimen(
			String name, 
			String type, 
			Integer subjectId) {
		
		Specimen sub = new Specimen();
		SpecimenExample subE = new SpecimenExample();
		subE.createCriteria().andSubjectIdEqualTo(subjectId).andNameEqualTo(name);
		List<Specimen> sl = spm.selectByExample(subE);
		if(sl.size() == 0) {
			sub.setName(name);
			sub.setCreationDate(new Date());
			sub.setCreatedBy(usm.getUserByName("").getUserId());
			sub.setSubjectId(subjectId);
			sub.setType(type);
			int rc = spm.insert(sub);
			if (rc == 1 ) {
				log.info("Inserting Specimen : " + name);
			} else {
				log.info("Failed to insert Specimen : " + name);
			}
		} else {
			sub = sl.get(0);
			log.info("Found Specimen : " + sl.get(0).getName());
		}
		return sub;
	}

	
	public RunOrder createRunOrder(
			Integer orderId,
			Integer procedureId,
			String type,
			Integer subjectId,
			String specimen,
			String description
			) throws Exception {
		RunOrder ro = new RunOrder();
		ro.setOrderId(orderId);
		ro.setProcedureId(procedureId);
		ro.setCreatedDate(new Date());
		ro.setCreatedBy(usm.getUserByName("").getUserId());
		ro.setStatus("New");
		ro.setType(type);
		ro.setSpecimenId(getSpecimen(specimen,"", subjectId).getSpecimenId());
		ro.setSiteId(1);
		
		Integer ep_id = eps.getActiveEndPoint();
		if(ep_id == null) throw new Exception("End Point Not Found.");
		ro.setEndPointId(ep_id);
		
		ro.setRunNotes(description);
		int rc = rom.insert(ro);
		if(rc != 1 ) {
			log.info("Failed to insert run order.");
			throw new Exception("Failed to insert RunOrder.");
		}
		return ro;
	}
	
	public int createRunResultLog(Integer runID, String notes) {
		
		if (notes != null ) {
		log.info("Run ID: " + runID + " Notes : " + notes);
		} else {
			log.info("Notes is NULL");
		}
		
		RunOrder rord = rom.selectByPrimaryKey(runID);
		rord.setResultNotes(notes);
		return rom.updateByPrimaryKey(rord);
		
	}
}

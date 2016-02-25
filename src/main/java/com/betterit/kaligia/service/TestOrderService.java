/**
 * 
 */
package com.betterit.kaligia.service;

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
	private UsersService us;
	
	@Autowired
	private SubjectMapper sm;
	
	@Autowired
	private SpecimenMapper spm;
	
	
	public TestOrder createTestOrder(
			String orderNo,
			String description,
			String subject
			) {
		
		TestOrder tord = new TestOrder();
		
		tord.setOrderNo(orderNo);
		tord.setDescription(description);
		tord.setCreationDate(new Date());
		tord.setCreatedBy(us.getUserByName("").getUserId());
		tord.setSiteId(1); // ToDo: Hard Coded to 1
		tord.setSubjectId(getSubject(subject).getSubjectId());
		int rc = tom.insert(tord);
		return tord;
		
	}

	public Subject getSubject(String name) {
		Subject sub = new Subject();
		SubjectExample subE = new SubjectExample();
		subE.createCriteria().andNameEqualTo(name);
		List<Subject> sl = sm.selectByExample(subE);
		if(sl.size() == 0) {
			sub.setName(name);
			sub.setCreationDate(new Date());
			sub.setCreatedBy(us.getUserByName("").getUserId());
			int rc = sm.insert(sub);
			log.info("Inserting Subject : " + name);
			
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
		subE.createCriteria().andNameEqualTo(name);
		List<Specimen> sl = spm.selectByExample(subE);
		if(sl.size() == 0) {
			sub.setName(name);
			sub.setCreationDate(new Date());
			sub.setCreatedBy(us.getUserByName("").getUserId());
			sub.setSubjectId(subjectId);
			sub.setType(type);
			int rc = spm.insert(sub);
			log.info("Inserting Specimen : " + name);
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
			String specimen
			) {
		RunOrder ro = new RunOrder();
		ro.setOrderId(orderId);
		ro.setProcedureId(procedureId);
		ro.setCreatedDate(new Date());
		ro.setCreatedBy(us.getUserByName("").getUserId());
		ro.setStatus("New");
		ro.setType(type);
		ro.setSpecimenId(getSpecimen(specimen,"", subjectId).getSpecimenId());
		ro.setSiteId(1);
		int rc = rom.insert(ro);
		return ro;
	}
}

/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.segmentParams;
import com.betterit.kaligia.dao.model.kaligia.ProcSegment;
import com.betterit.kaligia.dao.model.kaligia.ProcSegmentExample;
import com.betterit.kaligia.dao.model.kaligia.TestDevices;
import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.dao.model.kaligia.TestProcedureExample;
import com.betterit.kaligia.dao.model.kaligia.TestSegment;
import com.betterit.kaligia.dao.model.kaligia.TestSegmentSpec;
import com.betterit.kaligia.dao.model.kaligia.TestSegmentSpecExample;
import com.betterit.kaligia.dao.model.kaligia.Users;
import com.betterit.kaligia.dao.model.kaligia.UsersExample;
import com.betterit.kaligia.dao.repository.kaligia.ProcSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestDevicesMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestProcedureMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestSegmentSpecMapper;
import com.betterit.kaligia.dao.repository.kaligia.UsersMapper;

/**
 * @author Kaide Johar
 *
 */
@Service
public class TestProcedureService {
	
	@Autowired
	private TestProcedureMapper tpm;
	
	@Autowired
	private ProcSegmentMapper psm;
	
	@Autowired
	private TestSegmentMapper tsm;
	
	@Autowired
	private TestSegmentSpecMapper tssm;
	
	@Autowired
	private UsersMapper um;
	
	@Autowired
	private TestDevicesMapper tdm;
	
	public List<TestProcedure> findAll() {
		
		List<TestProcedure> tpl = tpm.selectByExample(null);
		return tpl;
		
	}

	
	public List<TestProcedure> findAllByType(String type) {
		
		TestProcedureExample tpe = new TestProcedureExample();
		tpe.createCriteria().andTypeEqualTo(type);
		List<TestProcedure> tpl = tpm.selectByExample(tpe);
		return tpl;
		
	}

	public int runTestProcedure(Integer procedureID) {
		
		int rc = 0;
		
		// Get Test Procedure
		TestProcedure tp = tpm.selectByPrimaryKey(procedureID);
		
		//Get Segments and Params
		List<TestSegmentSpec> tssl = new ArrayList<TestSegmentSpec>();
		
		ProcSegmentExample pse = new ProcSegmentExample();
		pse.createCriteria().andProcedureIdEqualTo(procedureID);
		List<ProcSegment> psl = psm.selectByExample(pse);
		
		for(int i=0; i<psl.size(); i++) {
			// Get Segment Parameters
			TestSegmentSpecExample tsse = new TestSegmentSpecExample();
			tsse.createCriteria().andSegmentIdEqualTo(psl.get(i).getSegmentId());
			tssl = tssm.selectByExample(tsse);
		}
		
		
		
		
		
		return 0;
	}
	
	public List<TestSegment> getSegmentForProc(TestProcedure tp) {
		
		List<ProcSegment> psl;
		List<TestSegment> tsl = new ArrayList<TestSegment>();
		
		ProcSegmentExample pse = new ProcSegmentExample();
		pse.createCriteria().andProcedureIdEqualTo(tp.getProcedureId());
		psl = psm.selectByExample(pse);
		for(int i=0; i<psl.size(); i++) {
			tsl.add(tsm.selectByPrimaryKey(psl.get(i).getSegmentId()));
		}
		return tsl;
	}
	
	public int createTestProcedure(
			String name,
			String description,
			String type,
			String status,
			int noOfSegments,
			Integer spectrometerID,
			Integer laserID,
			Integer probeID,
			List<segmentParams> segParams
			) {
		
		int rc = 0;
		
		// User
		UsersExample ue = new UsersExample();
		ue.createCriteria().andNameEqualTo("Olesia Gololobova");
		List<Users> user=um.selectByExample(ue);
		if(user.size()==0) {
			//create user
			Users olesia = new Users();
			olesia.setName("Olesia Gololobova");
			olesia.setRole("Admin");
			rc = um.insert(olesia);
		}
		
		// Test Procedure
		TestProcedure tp = new TestProcedure();
		tp.setName(name);
		tp.setDescription(description);
		tp.setType(type);
		tp.setStatus(status);
		tp.setNoOfSegments(noOfSegments);
		tp.setCreationDate(new Date());
		tp.setCreatedBy(user.get(0).getUserId());
		
		rc = tpm.insert(tp);
		
		//Test Devices
		TestDevices td = new TestDevices();
		td.setProcedureId(tp.getProcedureId());
		td.setDeviceId(spectrometerID);
		tdm.insert(td);
		td.setDeviceId(laserID);
		tdm.insert(td);
		td.setDeviceId(probeID);
		tdm.insert(td);
		
		//Test Segment
		//Find segment with same params
		TestSegmentSpecExample tsse = new TestSegmentSpecExample();
		for(int jj=0; jj<segParams.size(); jj++) {
			
			List<Integer> sl = new ArrayList<Integer>();
					
			tsse.createCriteria().andNameEqualTo("IntegrationTime").andValueEqualTo(segParams.get(jj).getIntegrationTime()).andDeviceIdEqualTo(spectrometerID);
			List<TestSegmentSpec> tss1 = tssm.selectByExample(tsse);
			tsse.clear();
			if (tss1.size() ==  0) {
				// No segment found create new
				rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj);
				continue;
			};
			
			sl.clear();
			for(int kk=0; kk<tss1.size(); kk++) {
				sl.add(tss1.get(kk).getSegmentId());
			}
			
			tsse.createCriteria().andNameEqualTo("ScansToAverage").andValueEqualTo(segParams.get(jj).getScan2Average()).andDeviceIdEqualTo(spectrometerID).andSegmentIdIn(sl);
			List<TestSegmentSpec> tss2 = tssm.selectByExample(tsse);
			tsse.clear();
			if (tss2.size() == 0) {
				// No segment found create new
				rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj);
				continue;
			};

			sl.clear();
			for(int kk=0; kk<tss2.size(); kk++) {
				sl.add(tss1.get(kk).getSegmentId());
			}
			
			tsse.createCriteria().andNameEqualTo("BoxcarWidth").andValueEqualTo(segParams.get(jj).getBoxCarWidth()).andDeviceIdEqualTo(spectrometerID).andSegmentIdIn(sl);
			List<TestSegmentSpec> tss3 = tssm.selectByExample(tsse);
			tsse.clear();
			if (tss3.size() == 0) {
				// No segment found create new
				rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj);
				continue;
			};

			sl.clear();
			for(int kk=0; kk<tss3.size(); kk++) {
				sl.add(tss1.get(kk).getSegmentId());
			}
			
			tsse.createCriteria().andNameEqualTo("ElectricDark").andValueEqualTo(segParams.get(jj).getElectricDark()).andDeviceIdEqualTo(spectrometerID).andSegmentIdIn(sl);
			List<TestSegmentSpec> tss4 = tssm.selectByExample(tsse);
			tsse.clear();
			if (tss4.size() == 0) {
				// No segment found create new
				rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj);
				continue;
			};

			sl.clear();
			for(int kk=0; kk<tss4.size(); kk++) {
				sl.add(tss1.get(kk).getSegmentId());
			}
			
			tsse.createCriteria().andNameEqualTo("NonLinearityCorrection").andValueEqualTo(segParams.get(jj).getNonLinearCorrect()).andDeviceIdEqualTo(spectrometerID).andSegmentIdIn(sl);
			List<TestSegmentSpec> tss5 = tssm.selectByExample(tsse);
			tsse.clear();
			if (tss5.size() == 0) {
				// No segment found create new
				rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj);
				continue;
			};

			sl.clear();
			for(int kk=0; kk<tss5.size(); kk++) {
				sl.add(tss1.get(kk).getSegmentId());
			}
			
			tsse.createCriteria().andNameEqualTo("Power").andValueEqualTo(segParams.get(jj).getPower()).andDeviceIdEqualTo(laserID).andSegmentIdIn(sl);
			List<TestSegmentSpec> tss6 = tssm.selectByExample(tsse);
			tsse.clear();
			if (tss6.size() == 0) {
				// No segment found create new
				rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj);
				continue;
			};

			sl.clear();
			for(int kk=0; kk<tss6.size(); kk++) {
				sl.add(tss1.get(kk).getSegmentId());
			}
			
			tsse.createCriteria().andNameEqualTo("Delay").andValueEqualTo(segParams.get(jj).getDelay()).andSegmentIdIn(sl);
			List<TestSegmentSpec> tss7 = tssm.selectByExample(tsse);
			tsse.clear();
			if (tss7.size() == 0) {
				// No segment found create new
				rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj);
				continue;
			};
			
			//Create ProcSegment
			// Segment found, Map to Procedure
			ProcSegment ps = new ProcSegment();
			ps.setProcedureId(tp.getProcedureId());
			ps.setSegmentId(tss7.get(0).getSegmentId());
			ps.setSegmentNo(jj);
			rc = psm.insert(ps);
		}
		
		return 0;
	}

	public int createSegmentParam(TestProcedure tp, Integer spectrometerID, Integer laserID, segmentParams segParam, int segNo) {
		
		int rc = 0;
		
		//Create Segment
		TestSegment ts = new TestSegment();
		ts.setName(tp.getName() + " Segment" + segNo);
		ts.setDescription(tp.getDescription());
		ts.setCreatedBy(tp.getCreatedBy());
		ts.setCreationDate(tp.getCreationDate());
		rc = tsm.insert(ts);
		
		//Create Parameters
		TestSegmentSpec tss = new TestSegmentSpec();
		tss.setSegmentId(ts.getSegmentId());
		
		tss.setDeviceId(spectrometerID);
		tss.setName("IntegrationTime");
		tss.setValue(segParam.getIntegrationTime());
		tss.setUnit("s");
		rc = tssm.insert(tss);
		
		tss.setDeviceId(spectrometerID);
		tss.setName("ScansToAverage");
		tss.setValue(segParam.getScan2Average());
		tss.setUnit("");
		rc = tssm.insert(tss);
		
		tss.setDeviceId(spectrometerID);
		tss.setName("BoxcarWidth");
		tss.setValue(segParam.getBoxCarWidth());
		tss.setUnit("pixel");
		rc = tssm.insert(tss);
		
		tss.setDeviceId(spectrometerID);
		tss.setName("ElectricDark");
		tss.setValue(segParam.getElectricDark());
		tss.setUnit("");
		rc = tssm.insert(tss);
		
		tss.setDeviceId(spectrometerID);
		tss.setName("NonLinearityCorrection");
		tss.setValue(segParam.getNonLinearCorrect());
		tss.setUnit("");
		rc = tssm.insert(tss);
		
		tss.setDeviceId(laserID);
		tss.setName("Power");
		tss.setValue(segParam.getPower());
		tss.setUnit("mw");
		rc = tssm.insert(tss);
		
		tss.setDeviceId(null);
		tss.setName("Delay");
		tss.setValue(segParam.getDelay());
		tss.setUnit("s");
		rc = tssm.insert(tss);
		
		//Create ProcSegment
		ProcSegment ps = new ProcSegment();
		ps.setProcedureId(tp.getProcedureId());
		ps.setSegmentId(ts.getSegmentId());
		ps.setSegmentNo(segNo);
		rc = psm.insert(ps);
		
		return 0;
	};

}
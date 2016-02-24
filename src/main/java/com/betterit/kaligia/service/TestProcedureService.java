/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.betterit.kaligia.TestResult;
import com.betterit.kaligia.TestRun;
import com.betterit.kaligia.segmentParams;
import com.betterit.kaligia.dao.model.kaligia.ProcSegment;
import com.betterit.kaligia.dao.model.kaligia.ProcSegmentExample;
import com.betterit.kaligia.dao.model.kaligia.RunOrder;
import com.betterit.kaligia.dao.model.kaligia.RunSegment;
import com.betterit.kaligia.dao.model.kaligia.RunSegmentLog;
import com.betterit.kaligia.dao.model.kaligia.TestDevices;
import com.betterit.kaligia.dao.model.kaligia.TestOrder;
import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.dao.model.kaligia.TestProcedureExample;
import com.betterit.kaligia.dao.model.kaligia.TestSegment;
import com.betterit.kaligia.dao.model.kaligia.TestSegmentSpec;
import com.betterit.kaligia.dao.model.kaligia.TestSegmentSpecExample;
import com.betterit.kaligia.dao.model.kaligia.Users;
import com.betterit.kaligia.dao.model.kaligia.UsersExample;
import com.betterit.kaligia.dao.repository.kaligia.ProcSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.RunSegmentLogMapper;
import com.betterit.kaligia.dao.repository.kaligia.RunSegmentMapper;
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
	private UsersService us;
	
	@Autowired
	private TestOrderService tos;
	
	@Autowired
	private TestDevicesMapper tdm;
	
	@Autowired
	private RunSegmentMapper rsm;
	
	@Autowired
	private RunSegmentLogMapper rslm;
	
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
	
	public TestProcedure findByName(String name) {
		
		TestProcedureExample tpe = new TestProcedureExample();
		tpe.createCriteria().andNameEqualTo(name);
		List<TestProcedure> tpl = tpm.selectByExample(tpe);
		return tpl.get(0);
		
	}

	@Transactional(rollbackFor=Exception.class)
	public int runTestProcedure(
			String orderNo,
			String description,
			String type,
			Integer procedureID,
			String subject,
			String specimen
			) throws Exception {
		
		int rc = 0;
		
		// Get Test Procedure
		TestProcedure tp = tpm.selectByPrimaryKey(procedureID);
		
		//Get Segments and Params
		List<RunSegment> rsl = new ArrayList<RunSegment>();
		
		ProcSegmentExample pse = new ProcSegmentExample();
		pse.createCriteria().andProcedureIdEqualTo(procedureID);
		List<ProcSegment> psl = psm.selectByExample(pse);
				
		// Create Order
		TestOrder tord = tos.createTestOrder(orderNo, description, subject);
		
		// Create RunOrder
		RunOrder rord = tos.createRunOrder(tord.getOrderId(), procedureID, type, tord.getSubjectId(), specimen);
		
		// Create RunSegment
		for(int i=0; i<psl.size(); i++) {
			
			RunSegment rs = new RunSegment();
			rs.setRunId(rord.getRunId());
			rs.setRunNo(i+1);
			rs.setStatus("New");
			rs.setSegmentId(psl.get(i).getSegmentId());
			rc = rsm.insert(rs);
			rsl.add(rs);
		}

		
		// Do Run
		TestSegmentSpecExample tsse = new TestSegmentSpecExample();
		List<TestSegmentSpec> tssl = new ArrayList<TestSegmentSpec>();
		List<TestRun> trl = new ArrayList<TestRun>();
		List<TestResult> trsl = new ArrayList<TestResult>();
		Integer seg_run_id;
		Integer integrationTime; 
		Integer restTime;
		Integer scanToAverage;
		Integer darkCurrent;
		Integer nonLinear;
		Integer boxcarWidth;
		Integer spectrometerIndex=0;
		String spectrometerType="QEPro";
		
		for(int i=0; i<rsl.size(); i++) {
			
			seg_run_id = rsl.get(i).getRunSegmentId();
			
			tsse.clear();
			tssl.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("IntegrationTime");
			tssl = tssm.selectByExample(tsse);
			integrationTime = Integer.valueOf(tssl.get(0).getValue());
			
			tsse.clear();
			tssl.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("Delay");
			tssl = tssm.selectByExample(tsse);
			restTime = Integer.valueOf(tssl.get(0).getValue());
			
			tsse.clear();
			tssl.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("ScansToAverage");
			tssl = tssm.selectByExample(tsse);
			scanToAverage = Integer.valueOf(tssl.get(0).getValue());

			tsse.clear();
			tssl.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("ElectricDark");
			tssl = tssm.selectByExample(tsse);
			darkCurrent = Integer.valueOf(tssl.get(0).getValue());

			tsse.clear();
			tssl.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("NonLinearityCorrection");
			tssl = tssm.selectByExample(tsse);
			nonLinear = Integer.valueOf(tssl.get(0).getValue());

			tsse.clear();
			tssl.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("BoxcarWidth");
			tssl = tssm.selectByExample(tsse);
			boxcarWidth = Integer.valueOf(tssl.get(0).getValue());

			// Instantiate TestRun
			TestRun tr = new TestRun (
					seg_run_id,
					integrationTime, 
					restTime,
					scanToAverage,
					darkCurrent,
					nonLinear,
					boxcarWidth,
					spectrometerIndex,
					spectrometerType);
			
			trl.add(tr);
		}

		for(int i=0; i<trl.size(); i++) {
			// Run test
			TestResult result = trl.get(i).doTestRun();
			trsl.add(result);
		}
		
		RunSegmentLog rslo = new RunSegmentLog();
		for(int i=0; i<trsl.size(); i++) {
			// Create ResultLog
			int size = trsl.get(i).getWavelength().length;
			rslo.setRunSegmentId(trsl.get(i).getSeg_run_id());
			
			for(int j=0; j<size; j++) {
				rslo.setrIndex(j+1);
				rslo.setWavelength(trsl.get(i).getWavelength()[j]);
				rslo.setPhotonCount(trsl.get(i).getPhoton_count()[j]);
				rc = rslm.insert(rslo);
			}	
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
	
	@Transactional(rollbackFor=Exception.class)
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
			) throws Exception {
		
		int rc = 0;
				
		// User
		Users user = us.getUserByName("");
			
			// Test Procedure
			TestProcedure tp = new TestProcedure();
			tp.setName(name);
			tp.setDescription(description);
			tp.setType(type);
			tp.setStatus(status);
			tp.setNoOfSegments(noOfSegments);
			tp.setCreationDate(new Date());
			tp.setCreatedBy(user.getUserId());
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
					rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj+1);
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
					rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj+1);
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
					rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj+1);
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
					rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj+1);
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
					rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj+1);
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
					rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj+1);
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
					rc = createSegmentParam(tp, spectrometerID, laserID, segParams.get(jj), jj+1);
					continue;
				};
				
				//Create ProcSegment
				// Segment found, Map to Procedure
				ProcSegment ps = new ProcSegment();
				ps.setProcedureId(tp.getProcedureId());
				ps.setSegmentId(tss7.get(0).getSegmentId());
				ps.setSegmentNo(jj+1);
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
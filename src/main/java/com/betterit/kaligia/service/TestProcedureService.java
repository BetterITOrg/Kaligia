/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.betterit.kaligia.TestRun;
import com.betterit.kaligia.segmentParams;
import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.dao.model.kaligia.DeviceExample;
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
import com.betterit.kaligia.dao.model.kaligia.TmpTestResult;
import com.betterit.kaligia.dao.model.kaligia.TmpTestResultExample;
import com.betterit.kaligia.dao.model.kaligia.Users;
import com.betterit.kaligia.dao.model.kaligia.UsersExample;
import com.betterit.kaligia.dao.repository.kaligia.DeviceMapper;
import com.betterit.kaligia.dao.repository.kaligia.ProcSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.RunSegmentLogMapper;
import com.betterit.kaligia.dao.repository.kaligia.RunSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestDevicesMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestProcedureMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestSegmentSpecMapper;
import com.betterit.kaligia.dao.repository.kaligia.TmpTestResultMapper;
import com.betterit.kaligia.dao.repository.kaligia.UsersMapper;

/**
 * @author Kaide Johar
 *
 */
@Service
public class TestProcedureService {
	private static final Logger log = LoggerFactory.getLogger(TestProcedureService.class);
	
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
	
	@Autowired
	private TmpTestResultMapper tmpRM;
	
	@Autowired
	private DeviceMapper dm;
	
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
	public List<TestRun> runTestProcedure(
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
		log.info("Found Procedure : " + tp.getName());
		
		//Get Segments and Params
		List<RunSegment> rsl = new ArrayList<RunSegment>();
		
		ProcSegmentExample pse = new ProcSegmentExample();
		pse.createCriteria().andProcedureIdEqualTo(procedureID);
		List<ProcSegment> psl = psm.selectByExample(pse);
		for(int i=0; i<psl.size(); i++) {
			log.info("Found Proc Segment : " + psl.get(i).getSegmentId());
		}
				
		// Create Order
		TestOrder tord = tos.createTestOrder(orderNo, description, subject);
		
		// Create RunOrder
		RunOrder rord = tos.createRunOrder(tord.getOrderId(), procedureID, type, tord.getSubjectId(), specimen, description);
		
		// Create RunSegment
		for(int i=0; i<psl.size(); i++) {
			
			RunSegment rs = new RunSegment();
			rs.setRunId(rord.getRunId());
			rs.setRunNo(i+1);
			rs.setStatus("New");
			rs.setSegmentId(psl.get(i).getSegmentId());
			rc = rsm.insert(rs);
			rsl.add(rs);
			log.info("Added Run Segment : " + rsl.get(i).getRunSegmentId());
		}

		
		// Create Test Run
		TestSegmentSpecExample tsse = new TestSegmentSpecExample();
		List<TestRun> trl = new ArrayList<TestRun>();
		Integer seg_run_id;
		Integer integrationTime; 
		Integer restTime;
		Integer scanToAverage;
		Integer darkCurrent;
		Integer nonLinear;
		Integer boxcarWidth;
		Integer spectrometerIndex=0;
		String spectrometerType = "QEPro";
		String labjackType = "U6";
		
		for(int i=0; i<rsl.size(); i++) {
			
			seg_run_id = rsl.get(i).getRunSegmentId();
			log.info("Run Segment ID : " + seg_run_id + "Segment ID : " + rsl.get(i).getSegmentId());
			
			tsse.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("IntegrationTime");
			List<TestSegmentSpec> tssi = tssm.selectByExample(tsse);
			integrationTime = Integer.valueOf(tssi.get(0).getValue());
			
			tsse.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("Delay");
			List<TestSegmentSpec> tssd = tssm.selectByExample(tsse);
			restTime = Integer.valueOf(tssd.get(0).getValue());
			
			tsse.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("ScansToAverage");
			List<TestSegmentSpec> tssa = tssm.selectByExample(tsse);
			scanToAverage = Integer.valueOf(tssa.get(0).getValue());

			tsse.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("ElectricDark");
			List<TestSegmentSpec> tssl = tssm.selectByExample(tsse);
			darkCurrent = Integer.valueOf(tssl.get(0).getValue());

			tsse.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("NonLinearityCorrection");
			List<TestSegmentSpec> tssn = tssm.selectByExample(tsse);
			nonLinear = Integer.valueOf(tssn.get(0).getValue());

			tsse.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("BoxcarWidth");
			List<TestSegmentSpec> tssb = tssm.selectByExample(tsse);
			boxcarWidth = Integer.valueOf(tssb.get(0).getValue());
			
			tsse.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("SpectrometerType");
			List<TestSegmentSpec> tsst = tssm.selectByExample(tsse);
			spectrometerType = tsst.get(0).getValue();
			
			tsse.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("LabJackType");
			List<TestSegmentSpec> tssj = tssm.selectByExample(tsse);
			labjackType = tssj.get(0).getValue();

			// Instantiate TestRun
			TestRun tr = new TestRun (
					rord.getRunId(),
					seg_run_id,
					integrationTime, 
					restTime,
					scanToAverage,
					darkCurrent,
					nonLinear,
					boxcarWidth,
					spectrometerIndex,
					spectrometerType,
					labjackType);
			
			trl.add(tr);
		}

		// Do Run
		for(int i=0; i<trl.size(); i++) {
			// Run test
			rc = trl.get(i).doTestRun();
			
			// Generate dummy test result since equipment is not connected
			/*
			TmpTestResultExample tmpe = new TmpTestResultExample();
			tmpe.createCriteria().andRunIdEqualTo(346+i);
			List<TmpTestResult> tmpTR = tmpRM.selectByExample(tmpe);
			trl.get(i).initWaveSpectra(tmpTR.size());
			for(int j=0; j<tmpTR.size(); j++) {
				trl.get(i).setWavelength(tmpTR.get(j).getWavenumber(), j);
				trl.get(i).setSpectra(tmpTR.get(j).getPhotonCount(), j);
			}
			*/
		}
		
		// Store Results
		RunSegmentLog rslo = new RunSegmentLog();
		for(int i=0; i<trl.size(); i++) {
			// Create ResultLog
			int size = trl.get(i).getWavelength().length;
			rslo.setRunSegmentId(trl.get(i).getSeg_run_id());
			
			for(int j=0; j<size; j++) {
				rslo.setrIndex(j+1);
				rslo.setWavelength(trl.get(i).getWavelength()[j]);
				rslo.setPhotonCount(trl.get(i).getSpectra()[j]);
				rc = rslm.insert(rslo);
			}	
		}
		
		return trl;
	}
	
	public List<TestSegment> getSegmentForProc(TestProcedure tp) {
		
		List<TestSegment> tsl = new ArrayList<TestSegment>();
		
		ProcSegmentExample pse = new ProcSegmentExample();
		pse.createCriteria().andProcedureIdEqualTo(tp.getProcedureId());
		List<ProcSegment> psl = psm.selectByExample(pse);
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
			Integer labjackID,
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
			td.setDeviceId(labjackID);
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
					rc = createSegmentParam(
							tp, 
							spectrometerID, 
							laserID, 
							labjackID, 
							segParams.get(jj), 
							jj+1);
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
					rc = createSegmentParam(
							tp, 
							spectrometerID, 
							laserID, 
							labjackID, 
							segParams.get(jj), 
							jj+1);
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
					rc = createSegmentParam(
							tp, 
							spectrometerID, 
							laserID, 
							labjackID, 
							segParams.get(jj), 
							jj+1);
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
					rc = createSegmentParam(
							tp, 
							spectrometerID, 
							laserID, 
							labjackID, 
							segParams.get(jj), 
							jj+1);
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
					rc = createSegmentParam(
							tp, 
							spectrometerID, 
							laserID, 
							labjackID, 
							segParams.get(jj), 
							jj+1);
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
					rc = createSegmentParam(tp, spectrometerID, laserID, labjackID, segParams.get(jj), jj+1);
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
					rc = createSegmentParam(tp, spectrometerID, laserID, labjackID, segParams.get(jj), jj+1);
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

	public int createSegmentParam(
			TestProcedure tp, 
			Integer spectrometerID, 
			Integer laserID, 
			Integer labjackID, 
			segmentParams segParam, 
			int segNo) {
		
		int rc = 0;
		String spectrometerType="QEPro";

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
		
		//Get Spectrometer type
		Device spectrometer = dm.selectByPrimaryKey(spectrometerID);
		switch(spectrometer.getModel()) {
			case "QE Pro" : spectrometerType = "QEPro"; break;
			case "Maya 2000Pro" : spectrometerType = "MAYA"; break;	
		}
		
		tss.setDeviceId(spectrometerID);
		tss.setName("SpectrometerType");
		tss.setValue(spectrometerType);
		tss.setUnit("");
		rc = tssm.insert(tss);

		//Get labjack type
		Device labjack = dm.selectByPrimaryKey(labjackID);
		String labjackType = labjack.getModel();
		tss.setDeviceId(labjackID);
		tss.setName("LabJackType");
		tss.setValue(labjackType);
		tss.setUnit("");
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
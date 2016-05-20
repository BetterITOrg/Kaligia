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
import org.springframework.transaction.annotation.Transactional;

import com.betterit.kaligia.FLRemoval;
import com.betterit.kaligia.ProcedureDetail;
import com.betterit.kaligia.TestRun;
import com.betterit.kaligia.segmentParams;
import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.dao.model.kaligia.EndPointDevices;
import com.betterit.kaligia.dao.model.kaligia.EndPointDevicesExample;
import com.betterit.kaligia.dao.model.kaligia.EndPointProcs;
import com.betterit.kaligia.dao.model.kaligia.FLRemovedLog;
import com.betterit.kaligia.dao.model.kaligia.ProcSegment;
import com.betterit.kaligia.dao.model.kaligia.ProcSegmentExample;
import com.betterit.kaligia.dao.model.kaligia.RunDevices;
import com.betterit.kaligia.dao.model.kaligia.RunOrder;
import com.betterit.kaligia.dao.model.kaligia.RunSegment;
import com.betterit.kaligia.dao.model.kaligia.RunSegmentLog;
import com.betterit.kaligia.dao.model.kaligia.SubjectLog;
import com.betterit.kaligia.dao.model.kaligia.TestDevices;
import com.betterit.kaligia.dao.model.kaligia.TestDevicesExample;
import com.betterit.kaligia.dao.model.kaligia.TestOrder;
import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.dao.model.kaligia.TestProcedureExample;
import com.betterit.kaligia.dao.model.kaligia.TestProcedureSpecs;
import com.betterit.kaligia.dao.model.kaligia.TestProcedureSpecsExample;
import com.betterit.kaligia.dao.model.kaligia.TestSegment;
import com.betterit.kaligia.dao.model.kaligia.TestSegmentSpec;
import com.betterit.kaligia.dao.model.kaligia.TestSegmentSpecExample;
import com.betterit.kaligia.dao.model.kaligia.TmpTestResult;
import com.betterit.kaligia.dao.model.kaligia.TmpTestResultExample;
import com.betterit.kaligia.dao.model.kaligia.Users;
import com.betterit.kaligia.dao.repository.kaligia.DeviceMapper;
import com.betterit.kaligia.dao.repository.kaligia.EndPointDevicesMapper;
import com.betterit.kaligia.dao.repository.kaligia.EndPointProcsMapper;
import com.betterit.kaligia.dao.repository.kaligia.FLRemovedLogMapper;
import com.betterit.kaligia.dao.repository.kaligia.ProcSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.RunDevicesMapper;
import com.betterit.kaligia.dao.repository.kaligia.RunSegmentLogMapper;
import com.betterit.kaligia.dao.repository.kaligia.RunSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.SubjectLogMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestDevicesMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestProcedureMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestProcedureSpecsMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestSegmentMapper;
import com.betterit.kaligia.dao.repository.kaligia.TestSegmentSpecMapper;
import com.betterit.kaligia.dao.repository.kaligia.TmpTestResultMapper;

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
	private FLRemovedLogMapper flrlm;
	
	@Autowired
	private TmpTestResultMapper tmpRM;
	
	@Autowired
	private DeviceMapper dm;
	
	@Autowired
	private SubjectLogMapper slm;
	
	@Autowired
	private EndPointProcsMapper eppm;
	
	@Autowired
	private EndPointDevicesMapper epdm;
	
	@Autowired
	private RunDevicesMapper rdm;
	
	@Autowired
	private TestProcedureSpecsMapper tpsm;

	
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
	
	public List<TestProcedure> findAllByStatus(String type) {
		
		TestProcedureExample tpe = new TestProcedureExample();
		tpe.createCriteria().andStatusEqualTo(type);
		List<TestProcedure> tpl = tpm.selectByExample(tpe);
		return tpl;
		
	}

	public TestProcedure findByName(String name) {
		
		TestProcedureExample tpe = new TestProcedureExample();
		tpe.createCriteria().andNameEqualTo(name);
		List<TestProcedure> tpl = tpm.selectByExample(tpe);
		if(tpl.size() == 0) {
			log.info("Procedure not found: " + name);
			return null;
		}
		return tpl.get(0);
		
	}

	@Transactional(rollbackFor=Exception.class)
	public List<TestRun> runTestProcedure(
			String orderNo,
			String description,
			String type,
			Integer procedureID,
			String patientID,
			String dateOfBirth,
			String patientEthnicity,
			String patientGender,
			String patientHeight,
			String patientWeight,
			String patientTemp,
			String patientHeartRate,
			String patientOLevel,
			String diastolicBP,
			String systolicBP,
			String skinColor,
			String specimen,
			String luminosity,
			String red,
			String blue,
			String green,
			String imageFile
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
		TestOrder tord = tos.createTestOrder(
												orderNo, 
												description, 
												patientID,
												dateOfBirth,
												patientEthnicity,
												patientGender
												);
		
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
			if(rc != 1) {
				log.info("Failed to insert run segment.");
			}
			rsl.add(rs);
			log.info("Added Run Segment : " + rsl.get(i).getRunSegmentId());
		}

		//Create Subject Log
		SubjectLog sublog = new SubjectLog();
		sublog.setSubjectId(tord.getSubjectId());
		sublog.setOrderId(tord.getOrderId());
		sublog.setCreatedBy(us.getUserByName("").getUserId());
		sublog.setCreationDate(new Date());
		
		//String patientHeight,
		sublog.setName("Height");
		sublog.setValue(patientHeight);
		sublog.setUnit("inches");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject height.");
		}
		
		//String patientWeight,
		sublog.setName("Weight");
		sublog.setValue(patientWeight);
		sublog.setUnit("lb");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject weight.");
		}

		//String patientTemp,
		sublog.setName("Temperature");
		sublog.setValue(patientTemp);
		sublog.setUnit("farenheit");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject temp.");
		}

		//String patientHeartRate,
		sublog.setName("HeartRate");
		sublog.setValue(patientHeartRate);
		sublog.setUnit("bpm");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject heart rate.");
		}

		//String patientOLevel,
		sublog.setName("OxygenLevel");
		sublog.setValue(patientOLevel);
		sublog.setUnit("percent");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject oxygen level.");
		}

		//String diastolicBP,
		sublog.setName("DiastolicBP");
		sublog.setValue(diastolicBP);
		sublog.setUnit("mmHg");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject BP.");
		}

		//String systolicBP,
		sublog.setName("SystolicBP");
		sublog.setValue(systolicBP);
		sublog.setUnit("mmHg");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject BP.");
		}

		//String skinColor,
		sublog.setName("SkinColor");
		sublog.setValue(skinColor);
		sublog.setUnit("");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject Skin Color.");
		}

		//String skinColor,
		sublog.setName("Luminosity");
		sublog.setValue(luminosity);
		sublog.setUnit("");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject Luminosity.");
		}
				
		//String skinColor,
		sublog.setName("Red");
		sublog.setValue(red);
		sublog.setUnit("");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject Red Color.");
		}
			
		//String skinColor,
		sublog.setName("Blue");
		sublog.setValue(blue);
		sublog.setUnit("");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject Blue Color.");
		}
			
		//String skinColor,
		sublog.setName("Green");
		sublog.setValue(green);
		sublog.setUnit("");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject Green Color.");
		}
		
		//String skinColor,
		sublog.setName("ImageFile");
		sublog.setValue(imageFile);
		sublog.setUnit("");
		rc = slm.insert(sublog);
		if(rc != 1) {
			log.info("Failed to insert subject Image File.");
		}
		
		//Create RunDevices
		// Get EndPointDevices
		List<EndPointDevices> epdl = new ArrayList<EndPointDevices>();
		EndPointDevicesExample epde = new EndPointDevicesExample();
		epde.createCriteria().andEndPointIdEqualTo(1);
		epdl = epdm.selectByExample(epde);
	
		//Map to Run ID
		RunDevices rd = new RunDevices();
		rd.setRunId(rord.getRunId());
		for(int i=0; i<epdl.size(); i++) {
			rd.setDeviceInstId(epdl.get(i).getDeviceInstId());
			rc = rdm.insert(rd);
			if(rc != 1) {
				log.info("Failed to insert run device.");
			}
		};
		
		// Create Test Run
		TestSegmentSpecExample tsse = new TestSegmentSpecExample();
		List<TestRun> trl = new ArrayList<TestRun>();
		Integer seg_run_id;
		Double integrationTime; 
		Integer restTime;
		Integer scanToAverage;
		Integer darkCurrent;
		Integer nonLinear;
		Integer boxcarWidth;
		Double laserPower;
		Integer spectrometerIndex=0;
		String spectrometerType = "QEPro";
		String labjackType = "U6";
		
		for(int i=0; i<rsl.size(); i++) {
			
			seg_run_id = rsl.get(i).getRunSegmentId();
			log.info("Run Segment ID : " + seg_run_id + "Segment ID : " + rsl.get(i).getSegmentId());
			
			tsse.clear();
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("IntegrationTime");
			List<TestSegmentSpec> tssi = tssm.selectByExample(tsse);
			integrationTime = Double.valueOf(tssi.get(0).getValue());
			
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
			tsse.createCriteria().andSegmentIdEqualTo(rsl.get(i).getSegmentId()).andNameEqualTo("Power");
			List<TestSegmentSpec> tssp = tssm.selectByExample(tsse);
			laserPower = Double.valueOf(tssp.get(0).getValue());
			
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
					laserPower,
					spectrometerIndex,
					spectrometerType,
					labjackType);
			
			trl.add(tr);
		}

		// Do Run
		for(int i=0; i<trl.size(); i++) {
			// Run test
			
			rc = trl.get(i).doTestRun();
			if (rc == 100) {
				throw new Exception("Device Initialization Failed.");
			}
			
			
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
		FLRemovedLog flrl = new FLRemovedLog();
		
		for(int i=0; i<trl.size(); i++) {
			// Create ResultLog
			int size = trl.get(i).getWavelength().length;
			rslo.setRunSegmentId(trl.get(i).getSeg_run_id());
			
			for(int j=0; j<size; j++) {
				rslo.setrIndex(j+1);
				rslo.setWavelength(trl.get(i).getWavelength()[j]);
				rslo.setPhotonCount(trl.get(i).getSpectra()[j]);
				rc = rslm.insert(rslo);
				if(rc != 1) {
					log.info("Failed to insert results.");
				}

			}
			
			// Get Procedure Parameters
			int startPos = 200;
			int endPos = 1000;
			double threshold = 0.0001;
			int exPower = 6;
			
			TestProcedureSpecsExample tpspecE = new TestProcedureSpecsExample();
			tpspecE.createCriteria().andProcedureIdEqualTo(tp.getProcedureId());
			List<TestProcedureSpecs> tpspecL = tpsm.selectByExample(tpspecE); 
			for(int l=0; l<tpspecL.size(); l++) {
				switch(tpspecL.get(l).getName()) {
				case "FLRStartPosition" : startPos = Integer.valueOf(tpspecL.get(l).getValue()); break;
				case "FLREndPosition" : endPos = Integer.valueOf(tpspecL.get(l).getValue()); break;
				case "FLRThreshold" : threshold = Double.valueOf(tpspecL.get(l).getValue()); break;
				}
			}
			
			// Store FLRemovedLog
			FLRemoval flr = new FLRemoval(
					trl.get(i).getWavelength(),
					trl.get(i).getSpectra(),
					startPos,
					endPos,
					threshold,
					exPower
					);
			flr.removeFL();
			
			trl.get(i).setFlrwavelength(flr.getThisXaxis());
			trl.get(i).setFlrspectra(flr.getSpecNoBk());
			
			int fsize = flr.getThisXaxis().length;
			flrl.setRunSegmentId(trl.get(i).getSeg_run_id());
			
			for(int j=0; j<fsize; j++) {
				flrl.setrIndex(j+1);
				flrl.setWavelength(flr.getThisXaxis()[j]);
				flrl.setPhotonCount(flr.getSpecNoBk()[j]);
				rc = flrlm.insert(flrl);
				if(rc != 1) {
					log.info("Failed to insert flremovedlog.");
				}

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
			Integer collectFiberID,
			Integer exciteFiberID,
			Integer tubeID,
			String startPos,
			String endPos,
			String threshold,
			String totalRunTime,
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
			if(rc != 1) {
				log.info("Failed to insert test procedure.");
			}

			
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
			td.setDeviceId(collectFiberID);
			tdm.insert(td);
			td.setDeviceId(exciteFiberID);
			tdm.insert(td);
			td.setDeviceId(tubeID);
			tdm.insert(td);
			// Procedure Parameters
			TestProcedureSpecs specs = new TestProcedureSpecs();
			specs.setProcedureId(tp.getProcedureId());
			specs.setName("FLRStartPosition");
			specs.setValue(startPos);
			rc = tpsm.insert(specs);
			specs.setName("FLREndPosition");
			specs.setValue(endPos);
			rc = tpsm.insert(specs);
			specs.setName("FLRThreshold");
			specs.setValue(threshold);
			rc = tpsm.insert(specs);
			specs.setName("TotalRunTime");
			specs.setValue(totalRunTime);
			rc = tpsm.insert(specs);
			
			//Test Segment
			//Find segment with same parameters
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
				
				log.info("Found Segment for IntegrationTime : " + segParams.get(jj).getIntegrationTime() + " for device : " + spectrometerID);
				
				sl.clear();
				for(int kk=0; kk<tss1.size(); kk++) {
					sl.add(tss1.get(kk).getSegmentId());
					//log.info("Segment Id[" + kk +"] = " + tss1.get(kk).getSegmentId());
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

				log.info("Found Segment for ScansToAverage : " + segParams.get(jj).getScan2Average() + " for device : " + spectrometerID);
				
				sl.clear();
				for(int kk=0; kk<tss2.size(); kk++) {
					sl.add(tss2.get(kk).getSegmentId());					
					//log.info("Segment Id[" + kk +"] = " + tss2.get(kk).getSegmentId());

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

				log.info("Found Segment for BoxcarWidth : " + segParams.get(jj).getBoxCarWidth() + " for device : " + spectrometerID);
				

				sl.clear();
				for(int kk=0; kk<tss3.size(); kk++) {
					sl.add(tss3.get(kk).getSegmentId());
					//log.info("Segment Id[" + kk +"] = " + tss3.get(kk).getSegmentId());

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

				log.info("Found Segment for ElectricDark : " + segParams.get(jj).getElectricDark() + " for device : " + spectrometerID);
				

				sl.clear();
				for(int kk=0; kk<tss4.size(); kk++) {
					sl.add(tss4.get(kk).getSegmentId());
					//log.info("Segment Id[" + kk +"] = " + tss4.get(kk).getSegmentId());

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

				log.info("Found Segment for NonLinearityCorrection : " + segParams.get(jj).getNonLinearCorrect() + " for device : " + spectrometerID);
				

				sl.clear();
				for(int kk=0; kk<tss5.size(); kk++) {
					sl.add(tss5.get(kk).getSegmentId());
					//log.info("Segment Id[" + kk +"] = " + tss5.get(kk).getSegmentId());

				}
				
				tsse.createCriteria().andNameEqualTo("Power").andValueEqualTo(segParams.get(jj).getPower()).andDeviceIdEqualTo(laserID).andSegmentIdIn(sl);
				List<TestSegmentSpec> tss6 = tssm.selectByExample(tsse);
				tsse.clear();
				if (tss6.size() == 0) {
					// No segment found create new
					rc = createSegmentParam(tp, spectrometerID, laserID, labjackID, segParams.get(jj), jj+1);
					continue;
				};

				log.info("Found Segment for Power : " + segParams.get(jj).getPower() + " for device : " + laserID);
				

				sl.clear();
				for(int kk=0; kk<tss6.size(); kk++) {
					sl.add(tss6.get(kk).getSegmentId());
					//log.info("Segment Id[" + kk +"] = " + tss6.get(kk).getSegmentId());

				}
				
				tsse.createCriteria().andNameEqualTo("Delay").andValueEqualTo(segParams.get(jj).getDelay()).andSegmentIdIn(sl);
				List<TestSegmentSpec> tss7 = tssm.selectByExample(tsse);
				tsse.clear();
				if (tss7.size() == 0) {
					// No segment found create new
					rc = createSegmentParam(tp, spectrometerID, laserID, labjackID, segParams.get(jj), jj+1);
					continue;
				};

				log.info("Found Segment for Delay : " + segParams.get(jj).getDelay() + " for device : ");
				
				sl.clear();
				for(int kk=0; kk<tss7.size(); kk++) {
					sl.add(tss7.get(kk).getSegmentId());
					//log.info("Segment Id[" + kk +"] = " + tss7.get(kk).getSegmentId());

				}


				//Create ProcSegment
				// Segment found, Map to Procedure
				ProcSegment ps = new ProcSegment();
				ps.setProcedureId(tp.getProcedureId());
				ps.setSegmentId(tss7.get(0).getSegmentId());
				ps.setSegmentNo(jj+1);
				rc = psm.insert(ps);
			}

		// For Now Add proc to endpoint (only one)
		EndPointProcs epp = new EndPointProcs();
		epp.setProcedureId(tp.getProcedureId());
		epp.setEndPointId(1);
		epp.setStatus("Active");
		epp.setCreatedBy(user.getUserId());
		epp.setCreationDate(new Date());
		rc = eppm.insert(epp);
		
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
		if(rc != 1) {
			log.info("Failed to insert test segment.");
		}

		//Create Parameters
		TestSegmentSpec tss = new TestSegmentSpec();
		tss.setSegmentId(ts.getSegmentId());
		
		tss.setDeviceId(spectrometerID);
		tss.setName("IntegrationTime");
		tss.setValue(segParam.getIntegrationTime());
		tss.setUnit("s");
		rc = tssm.insert(tss);
		if(rc != 1) {
			log.info("Failed to insert integration time.");
		}

		tss.setDeviceId(spectrometerID);
		tss.setName("ScansToAverage");
		tss.setValue(segParam.getScan2Average());
		tss.setUnit("");
		rc = tssm.insert(tss);
		if(rc != 1) {
			log.info("Failed to insert scan 2 average.");
		}

		tss.setDeviceId(spectrometerID);
		tss.setName("BoxcarWidth");
		tss.setValue(segParam.getBoxCarWidth());
		tss.setUnit("pixel");
		rc = tssm.insert(tss);
		if(rc != 1) {
			log.info("Failed to insert boxcarwidth.");
		}

		tss.setDeviceId(spectrometerID);
		tss.setName("ElectricDark");
		tss.setValue(segParam.getElectricDark());
		tss.setUnit("");
		rc = tssm.insert(tss);
		if(rc != 1) {
			log.info("Failed to insert Electric Dark.");
		}

		tss.setDeviceId(spectrometerID);
		tss.setName("NonLinearityCorrection");
		tss.setValue(segParam.getNonLinearCorrect());
		tss.setUnit("");
		rc = tssm.insert(tss);
		if(rc != 1) {
			log.info("Failed to insert Non Linear Corr.");
		}

		tss.setDeviceId(laserID);
		tss.setName("Power");
		tss.setValue(segParam.getPower());
		tss.setUnit("mw");
		rc = tssm.insert(tss);
		if(rc != 1) {
			log.info("Failed to insert power.");
		}

		tss.setDeviceId(null);
		tss.setName("Delay");
		tss.setValue(segParam.getDelay());
		tss.setUnit("s");
		rc = tssm.insert(tss);
		if(rc != 1) {
			log.info("Failed to insert delay.");
		}

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

	public ProcedureDetail getProcedureDetail(Integer procedure_id) {
		ProcedureDetail procDtls = new ProcedureDetail();
		
		//Get TestProcedure
		TestProcedure tp = new TestProcedure();
		tp = tpm.selectByPrimaryKey(procedure_id);
		procDtls.setProcedureID(procedure_id);
		procDtls.setName(tp.getName());
		procDtls.setDescription(tp.getDescription());
		procDtls.setStatus(tp.getStatus());
		procDtls.setType(tp.getType());
		procDtls.setNoOfSegments(tp.getNoOfSegments());
		
		//Get TestDevices 
		List<TestDevices> tdl = new ArrayList<TestDevices>();
		TestDevicesExample tde = new TestDevicesExample();
		tde.createCriteria().andProcedureIdEqualTo(procedure_id);
		tdl = tdm.selectByExample(tde);
		
		for(int i=0; i<tdl.size(); i++) {
			
			Device device = new Device();
			device = dm.selectByPrimaryKey(tdl.get(i).getDeviceId());
			
			switch(device.getType()) {
			
				case "Laser" : procDtls.setLaser(device.getName()); break;
				case "Probe" : procDtls.setProbe(device.getName()); break;
				case "Spectrometer" : procDtls.setSpectrometer(device.getName()); break;
				case "LabJack" : procDtls.setLabjack(device.getName()); break;
				case "Tube" : procDtls.setTube(device.getName()); break;
				case "ExcitationFiber" : procDtls.setExcitationFiber(device.getName()); break;
				case "CollectionFiber" : procDtls.setCollectionFiber(device.getName()); break;
			}
		}
		
		//Get Procedure Parameters
		TestProcedureSpecsExample tpspecE = new TestProcedureSpecsExample();
		tpspecE.createCriteria().andProcedureIdEqualTo(tp.getProcedureId());
		List<TestProcedureSpecs> tpspecL = tpsm.selectByExample(tpspecE); 
		for(int l=0; l<tpspecL.size(); l++) {
			switch(tpspecL.get(l).getName()) {
			case "FLRStartPosition" : procDtls.setStartPos(tpspecL.get(l).getValue()); break;
			case "FLREndPosition" 	: procDtls.setEndPos(tpspecL.get(l).getValue()); break;
			case "FLRThreshold" 	: procDtls.setThreshold(tpspecL.get(l).getValue()); break;
			case "TotalRunTime" 	: procDtls.setTotalRunTime(tpspecL.get(l).getValue()); break;
			}
		}

		//Get Procedure Segments
		List<ProcSegment> psl = new ArrayList<ProcSegment>();
		ProcSegmentExample pse = new ProcSegmentExample();
		pse.createCriteria().andProcedureIdEqualTo(procedure_id);
		psl = psm.selectByExample(pse);
		
		//Get Segment Parameters
		List<segmentParams> spl = new ArrayList<segmentParams>();
		for(int j=0; j<psl.size(); j++) {
			
			segmentParams sp = new segmentParams();
			
			List<TestSegmentSpec> tssl = new ArrayList<TestSegmentSpec>();
			TestSegmentSpecExample tsse = new TestSegmentSpecExample();
			tsse.createCriteria().andSegmentIdEqualTo(psl.get(j).getSegmentId());
			tssl = tssm.selectByExample(tsse);
			
			for(int k=0; k<tssl.size(); k++) {
				switch(tssl.get(k).getName()) {
				case "IntegrationTime" : sp.setIntegrationTime(tssl.get(k).getValue()); break;
				case "ScansToAverage" : sp.setScan2Average(tssl.get(k).getValue()); break;
				case "BoxcarWidth" : sp.setBoxCarWidth(tssl.get(k).getValue()); break;
				case "ElectricDark" : sp.setElectricDark(tssl.get(k).getValue()); break;
				case "NonLinearityCorrection" : sp.setNonLinearCorrect(tssl.get(k).getValue()); break;
				case "Power" : sp.setPower(tssl.get(k).getValue()); break;
				case "Delay" : sp.setDelay(tssl.get(k).getValue()); break;
				}
				
			}
			
			spl.add(sp);
		
		}
		procDtls.setSegmentList(spl);
		return procDtls;
	}
}

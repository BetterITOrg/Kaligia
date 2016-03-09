/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.betterit.kaligia.KaligiaRunOrder;
import com.betterit.kaligia.TestRun;
import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.service.TestOrderService;
import com.betterit.kaligia.service.TestProcedureService;

/**
 * @author nayar
 *
 */

 @Controller
public class KaligiaRunOrderController {

	Logger log = Logger.getLogger(KaligiaRunOrderController.class.getName());

	
	@Autowired
	private TestProcedureService tps;
	
	@Autowired
	private TestOrderService tos;

	@RequestMapping(value="/KaligiaRunOrder", method=RequestMethod.GET)
    public String runOrderForm(Model model) {

		KaligiaRunOrder runOrderObj = new KaligiaRunOrder();
		
		model.addAttribute("RunOrder", runOrderObj);
				
		return ("KaligiaRunOrder");
	}

	@RequestMapping(value="/KaligiaRunOrder", method=RequestMethod.POST)
    public String handleRunForm(@ModelAttribute KaligiaRunOrder runOrderObject, Model model) throws Exception {
		
		log.info("In KaligiaRunOrder POST");
		log.info("received values" + runOrderObject.toString());
		
		//Find the testProcedure to run
		TestProcedure testProcObj= tps.findByName(runOrderObject.getTestProcedure());
		if (testProcObj == null)
		{
			log.info("TestProcedure not found for " + runOrderObject.getTestProcedure());
			return "KaligiaRunOrder";
		}
		
		runOrderObject.setTestProcedureId(testProcObj.getProcedureId());
			
		List<TestRun> trl = new ArrayList<TestRun>();
		try {
			trl = tps.runTestProcedure(
					runOrderObject.getOrderNo(), 
					runOrderObject.getDescription(), 
					runOrderObject.getType(), 
					runOrderObject.getTestProcedureId(),
					runOrderObject.getPatientId(),
					runOrderObject.getDateOfBirth(),
					runOrderObject.getPatientEthnicity(),
					runOrderObject.getPatientGender(),
					runOrderObject.getPatientHeight(),
					runOrderObject.getPatientWeight(),
					runOrderObject.getPatientTemp(),
					runOrderObject.getPatientHeartRate(),
					runOrderObject.getPatientOLevel(),
					runOrderObject.getDiastolicBP(),
					runOrderObject.getSystolicBP(),
					runOrderObject.getSkinColor(),
					runOrderObject.getSpecimen() 
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		runOrderObject.setRunID(trl.get(0).getRun_id());
		runOrderObject.setResultNotes("");
		log.info("setting Run ID: " + trl.get(0).getRun_id());
		
		for(int i=0; i<trl.size(); i++) {
			int wsize = trl.get(i).getWavelength().length;
			List<Integer> wave = new ArrayList<Integer>();
			List<Float> photon = new ArrayList<Float>();
			for(int j=0; j<wsize; j++) {
				int swave = (int)(12048 - (10000000.0/trl.get(i).getWavelength()[j]));
				if(swave < 200 ) continue;
				if(swave >1800 ) break;
				wave.add(swave);
				photon.add((float) (trl.get(i).getSpectra()[j]/10000.0));
			}
			
			int[] wavei = new int[wave.size()];
			float[] photoni= new float[wave.size()];
			for(int ii=0; ii<wave.size();ii++) {
				wavei[ii]=wave.get(ii);
				photoni[ii]=photon.get(ii);
			}
			runOrderObject.setTestStatus(trl.get(i).getStatus(), i);
			runOrderObject.setWavenumber(wavei, i);
			runOrderObject.setPhoton(photoni, i);
		}
		
		model.addAttribute("RunOrderResult", runOrderObject);
		return "KaligiaRunResult";
			
	}
	
	@RequestMapping(value="/KaligiaRunResult", method=RequestMethod.POST)
    public String handleRunNotes(@ModelAttribute KaligiaRunOrder runOrderObject, Model model) {

		log.info("Inside Handle Run Notes");
		log.info("Object :[" + runOrderObject.toString() + "]");
		
		try{
			
			int rc= tos.createRunResultLog(runOrderObject.getRunID(), 
					runOrderObject.getResultNotes());
			
			if(rc !=1 ) {
				log.info("Failed to update result log.");
			}
		}	
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		return "redirect:/KaligiaMainApp";
	}
}

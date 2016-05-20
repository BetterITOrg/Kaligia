/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.betterit.kaligia.KaligiaRunOrder;
import com.betterit.kaligia.TestRun;
import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.service.EndPointService;
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
	
	@Autowired
	private EndPointService eps;

	@RequestMapping(value="/KaligiaRunOrder", method=RequestMethod.GET)
    public String runOrderForm(HttpSession session, HttpServletRequest request, Model model) {

		KaligiaRunOrder runOrderObj = new KaligiaRunOrder();
        List<TestProcedure> procedureList = tps.findAllByStatus("ACTIVE");
        String uRole="";
        
        runOrderObj.setOrderNo(eps.getActiveEndPointName() + "-");
        model.addAttribute("ProcedureList", procedureList);
		model.addAttribute("RunOrder", runOrderObj);
		if (request.isUserInRole("ROLE_Admin")) {
			uRole="Admin";
		}
		else
		{
			uRole="Operator";
		}
		model.addAttribute("UserRole", uRole);
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
			throw (new Exception("Procedure Not found: " + runOrderObject.getTestProcedure()));
			// return "KaligiaRunOrder";
		}
		
		runOrderObject.setTestProcedureId(testProcObj.getProcedureId());
		runOrderObject.setType(testProcObj.getType());
			
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
					runOrderObject.getSpecimen(),
					runOrderObject.getLumosity(),
					runOrderObject.getRed(),
					runOrderObject.getBlue(),
					runOrderObject.getGreen(),
					runOrderObject.getImageFile()
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
			
			int flrsize = trl.get(i).getFlrwavelength().length;
			List<Integer> flrwave = new ArrayList<Integer>();
			List<Float> flrphoton = new ArrayList<Float>();
			for(int j=0; j<flrsize; j++) {
				int fswave = (int)(12048 - (10000000.0/trl.get(i).getFlrwavelength()[j]));
				if(fswave < 200 ) continue;
				if(fswave >1800 ) break;
				flrwave.add(fswave);
				flrphoton.add((float) (trl.get(i).getFlrspectra()[j]/10000.0));
			}
			
			int[] flrwavei = new int[flrwave.size()];
			float[] flrphotoni= new float[flrwave.size()];
			for(int ii=0; ii<flrwave.size();ii++) {
				flrwavei[ii]=flrwave.get(ii);
				flrphotoni[ii]=flrphoton.get(ii);
			}
			
			runOrderObject.setTestStatus(trl.get(i).getStatus(), i);
			runOrderObject.setWavenumber(wavei, i);
			runOrderObject.setPhoton(photoni, i);
			runOrderObject.setFlrWavenumber(flrwavei, i);
			runOrderObject.setFlrPhoton(flrphotoni, i);
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

/**
 * 
 */
package com.betterit.kaligia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.service.DeviceService;
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
    public String handleRunForm(@ModelAttribute KaligiaRunOrder runOrderObject, Model model) {
		
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
					runOrderObject.getSubject(),
					runOrderObject.getSpecimen() 
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		runOrderObject.setRunID(trl.get(0).getRun_id());
		runOrderObject.setResultNotes("");
		
		for(int i=0; i<trl.size(); i++) {
			int wsize = trl.get(i).getWavelength().length;
			int wave[] = new int[wsize];
			float photon[] = new float[wsize];
			for(int j=0; j<wsize; j++) {
				wave[j] = (int)(12048 - (10000000.0/trl.get(i).getWavelength()[j]));
				photon[j] = (float) (trl.get(i).getSpectra()[j]/10000.0);
			}
			runOrderObject.setTestStatus(trl.get(i).getStatus(), i);
			runOrderObject.setWavenumber(wave, i);
			runOrderObject.setPhoton(photon, i);
		}
		
		model.addAttribute("RunOrderResult", runOrderObject);
		return "KaligiaRunResult";
			
	}
	
	@RequestMapping(value="/KaligiaRunResult", method=RequestMethod.POST)
    public String handleRunNotes(@ModelAttribute KaligiaRunOrder runOrderObject, Model model) {

		try{
			int rc= tos.createRunResultLog(runOrderObject.getRunID(), 
					runOrderObject.getResultNotes());
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "KaligiaRunResult";
	}
}

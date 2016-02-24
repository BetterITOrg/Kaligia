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

	@RequestMapping(value="/KaligiaRunOrder", method=RequestMethod.GET)
    public String runOrderForm(Model model) {

	/** TO-DO get all devices from database. populate the drop downs on the form */
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
		
		/*
		
		createProcedureObject.setNoOfSegments( createProcedureObject.getSegmentList().size());
		
		log.info("AFTER CLEANUP VALUES ARE " + createProcedureObject.toString());
		//Call the createTestProcedure service
	
		int rc = tps.createTestProcedure(
				createProcedureObject.getName(), 
				createProcedureObject.getDescription(), 
				"IN-VIVO",  // ToDo: Hard Coded
				createProcedureObject.getStatus(), 
				createProcedureObject.getNoOfSegments(), 
				Integer.valueOf(createProcedureObject.getSpectrometer()), 
				Integer.valueOf(createProcedureObject.getLaser()),
				Integer.valueOf(createProcedureObject.getProbe()), 
				createProcedureObject.getSegmentList()
				);
		
		if (rc != 0)		
		{
			log.info("failed insert");
			return "CreateProcedure";
		}	
		*/
		
		return "KaligiaMainApp";
			
	}

}

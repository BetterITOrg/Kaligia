/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betterit.kaligia.KaligiaRunOrder;
import com.betterit.kaligia.RunResult;
import com.betterit.kaligia.service.TestOrderService;


/**
 * @author nayar
 *
 */

 @RestController
public class RunResultController {

	Logger log = Logger.getLogger(RunResultController.class.getName());
	@Autowired
	private TestOrderService tos;



	@RequestMapping(value="/getRunResults", method=RequestMethod.GET)
    public RunResult getRunResults(@RequestParam(value="orderId", defaultValue="0")String orderId) {

		log.info(" In getRunResults for order " + orderId);
		RunResult returnObj= new RunResult();		
		if (orderId.equals("0")){
			//TODO: error out that order id not passed
		}
		else 
		{
			//TODO: fetch order run data
			
		}
		//log.info("returned list is " + siteList.size());
		
		/*model.addAttribute("DeviceList", deviceList);
		model.addAttribute("endPoint", createSiteObj);*/
				
		return returnObj;
	}

	@RequestMapping(value="/ResultNotes", method=RequestMethod.POST)
    public String handleResultNotes(@ModelAttribute KaligiaRunOrder runOrderObject) {

		log.info("Inside  rest handleResultNotes");
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
		return runOrderObject.getResultNotes();
	}
}

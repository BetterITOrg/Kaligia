/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.betterit.kaligia.ProcedureDetail;
import com.betterit.kaligia.RunResult;
import com.betterit.kaligia.segmentParams;
import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.service.TestOrderService;
import com.betterit.kaligia.service.TestProcedureService;


/**
 * @author nayar
 *
 */

 @RestController
public class RunResultController {

	Logger log = Logger.getLogger(RunResultController.class.getName());
	@Autowired
	private TestOrderService tos;
	
	@Autowired
	private TestProcedureService tps;


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
	
	@RequestMapping(value="/RunTime", method=RequestMethod.GET)
    public String getProcedureRuntime(@RequestParam(value="procedureName") String runProcName) throws Exception {
		log.info("In getProcedureRuntime for " + runProcName);
		
		Integer totalTime=3;
		ProcedureDetail pdObj = new ProcedureDetail();
		
		try{
			TestProcedure tpObj = tps.findByName(runProcName);
			pdObj=tps.getProcedureDetail(tpObj.getProcedureId());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			String statusMessage="failed to find procedure for " + runProcName;
			log.info(statusMessage);
			e.printStackTrace();
			throw e;
		}
		
		Iterator<segmentParams> segmentIterator = pdObj.getSegmentList().iterator();
		while (segmentIterator.hasNext()) {
			segmentParams paramObject= segmentIterator.next();
			totalTime= totalTime + Integer.valueOf(paramObject.getIntegrationTime()) 
							* Integer.valueOf(paramObject.getScan2Average());
		}
		totalTime = totalTime + pdObj.getNoOfSegments();
		log.info("Total runtime for procedure is " + totalTime);
		return totalTime.toString();
	}
}

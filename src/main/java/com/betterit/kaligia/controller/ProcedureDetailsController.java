/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.betterit.kaligia.ProcedureDetail;
import com.betterit.kaligia.service.TestProcedureService;

/**
 * @author nayar
 *
 */

 @Controller
public class ProcedureDetailsController {

	Logger log = Logger.getLogger(ProcedureDetailsController.class.getName());

	
	@Autowired
	private TestProcedureService tps;
	

	@RequestMapping(value="/ProcedureDetails", method=RequestMethod.GET)
    public String procedureDetailsForm(@RequestParam("tpsId") int tp_id, Model model) {

		ProcedureDetail pdObj = new ProcedureDetail();
		log.info("In ProcedureDetails GET with tpsid" + tp_id);
		
		try{
			pdObj=tps.getProcedureDetail(tp_id);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String statusMessage="failed to find procedure for id " + tp_id;
			log.info(statusMessage);
			model.addAttribute("Status", statusMessage);
			return "ShowStatus";
		}
		
		log.info("found procedure Details with tpsid " + tp_id);
		log.info(pdObj.toString());
		
		model.addAttribute("ProcedureDetails", pdObj);
				
		return ("ProcedureDetails");
	}

}

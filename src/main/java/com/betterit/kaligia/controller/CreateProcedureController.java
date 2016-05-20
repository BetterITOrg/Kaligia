/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.betterit.kaligia.ProcedureDetail;
import com.betterit.kaligia.segmentParams;
import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.service.DeviceService;
import com.betterit.kaligia.service.TestProcedureService;

/**
 * @author nayar
 *
 */

@Controller
@PreAuthorize("hasAnyRole('ROLE_Admin')")
public class CreateProcedureController {

	Logger log = Logger.getLogger(CreateProcedureController.class.getName());

	@Autowired
	private DeviceService deviceServiceObject;
	
	@Autowired
	private TestProcedureService tps;

	@RequestMapping(value="/CreateProcedure", method=RequestMethod.GET)
    public String createProcedureForm(Model model) {

	/** TO-DO get all devices from database. populate the drop downs on the form */
		List<Device> deviceList = deviceServiceObject.findAll();
		List<segmentParams> segList = new ArrayList<segmentParams>();
		ProcedureDetail createProcObj = new ProcedureDetail();
		
		for(int i=1; i<11; i++){
			segmentParams segmentObj= new segmentParams();
			segList.add(segmentObj);
		}
		createProcObj.setSegmentList(segList);
		
		model.addAttribute("DeviceList", deviceList);
		model.addAttribute("Procedur", createProcObj);
				
		return ("CreateProcedure");
	}

	@RequestMapping(value="/CreateProcedure", method=RequestMethod.POST)
    public String handleProcedureForm(@ModelAttribute ProcedureDetail createProcedureObject, Model model) {
		
		log.info("In CreateProcedure POST");
		log.info("received values" + createProcedureObject.toString());
		String statusMessage="";
		Integer totalTime=3;
		
		//Clean up the empty segment lines
		//for ( int i = 1; i <=createProcedureObject.getSegmentList().size(); i++){
		//	if ( createProcedureObject.getSegmentList()[i].getIntegrationTime )
		//}
		
		Iterator<segmentParams> segmentIterator = createProcedureObject.getSegmentList().iterator();
		while (segmentIterator.hasNext()) {
			segmentParams paramObject= segmentIterator.next();
			if ( Double.valueOf(paramObject.getIntegrationTime()) <= 0)
			{
				segmentIterator.remove();
			}
			else
			{
				totalTime= totalTime + Integer.valueOf(paramObject.getIntegrationTime()) 
							* Integer.valueOf(paramObject.getScan2Average());
			}
		}
		
		createProcedureObject.setNoOfSegments( createProcedureObject.getSegmentList().size());
		totalTime = totalTime + createProcedureObject.getNoOfSegments();
		createProcedureObject.setTotalRunTime(totalTime.toString());
		log.info("AFTER CLEANUP VALUES ARE " + createProcedureObject.toString());
		//Call the createTestProcedure service
	
		int rc = 0;
		try {
			rc = tps.createTestProcedure(
					createProcedureObject.getName(), 
					createProcedureObject.getDescription(), 
					createProcedureObject.getType(),  
					createProcedureObject.getStatus(), 
					createProcedureObject.getNoOfSegments(), 
					Integer.valueOf(createProcedureObject.getSpectrometer()), 
					Integer.valueOf(createProcedureObject.getLaser()),
					Integer.valueOf(createProcedureObject.getProbe()), 
					Integer.valueOf(createProcedureObject.getLabjack()),
					Integer.valueOf(createProcedureObject.getCollectionFiber()),
					Integer.valueOf(createProcedureObject.getExcitationFiber()),
					Integer.valueOf(createProcedureObject.getTube()),
					createProcedureObject.getStartPos(),
					createProcedureObject.getEndPos(),
					createProcedureObject.getThreshold(),
					createProcedureObject.getTotalRunTime(),
					createProcedureObject.getSegmentList()
					);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (rc != 0)		
		{
			
			log.info("failed insert");
			statusMessage = "ERROR!! Procedure Creation failed for Input:  " + createProcedureObject.toString();
			model.addAttribute("Status", statusMessage);
			return "ShowStatus";
		}
		else
		{
			statusMessage = "SUCCESS!! Procedure Creation success for Input  " + createProcedureObject.getName();
		}
		
		log.info(statusMessage);
		//model.addAttribute("Status", statusMessage);
		//return "ShowStatus";
		return "redirect:/KaligiaMainApp";
	}

}

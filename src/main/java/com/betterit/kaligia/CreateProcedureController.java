/**
 * 
 */
package com.betterit.kaligia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.service.DeviceService;
import com.betterit.kaligia.service.TestProcedureService;

/**
 * @author nayar
 *
 */

 @Controller
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
		CreateProcedure createProcObj = new CreateProcedure();
		
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
    public String handleProcedureForm(@ModelAttribute CreateProcedure createProcedureObject, Model model) {
		
		log.info("In CreateProcedure POST");
		log.info("received values" + createProcedureObject.toString());
		
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
		
		return "results";
			
	}

}

/**
 * 
 */
package com.betterit.kaligia;

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

/**
 * @author nayar
 *
 */

 @Controller
public class CreateProcedureController {

	Logger log = Logger.getLogger(CreateProcedureController.class.getName());

	@Autowired
	private DeviceService deviceServiceObject;

	@RequestMapping(value="/CreateProcedure", method=RequestMethod.GET)
    public String createProcedureForm(Model model) {

	/** TO-DO get all devices from database. populate the drop downs on the form */
		List<Device> deviceList = deviceServiceObject.findAll();
		
		model.addAttribute("DeviceList", deviceList);
		model.addAttribute("Procedur", new CreateProcedure());
		return ("CreateProcedure");
	}

	@RequestMapping(value="/CreateProcedure", method=RequestMethod.POST)
    public String handleProcedureForm(@ModelAttribute CreateProcedure createProcedureObject, Model model) {
		
		log.info("In CreateProcedure POST");
		log.info("received values" + createProcedureObject.toString());
		
		// TODO call the createTestProcedure service
		
		return "results";
		
		
	}

}

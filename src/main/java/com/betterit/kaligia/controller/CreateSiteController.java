/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.dao.model.kaligia.Site;
import com.betterit.kaligia.service.DeviceService;
import com.betterit.kaligia.service.SiteService;
import com.betterit.kaligia.service.TestProcedureService;

/**
 * @author nayar
 *
 */

 @Controller
 @PreAuthorize("hasAnyRole('ROLE_Admin')")
public class CreateSiteController {

	Logger log = Logger.getLogger(CreateSiteController.class.getName());

	@Autowired
	private DeviceService deviceServiceObject;
	
	@Autowired
	private TestProcedureService tps;
	
	@Autowired
	private SiteService ss;

	@RequestMapping(value="/CreateSite", method=RequestMethod.GET)
    public String createSiteForm(Model model) {

	/** TO-DO get all device types from database. populate the drop downs on the form */
		List<Device> deviceList = deviceServiceObject.findAll();
		Site siteObj = new Site();
		log.info("In CreateSite GET");
		
		model.addAttribute("DeviceList", deviceList);
		model.addAttribute("Site", siteObj);
				
		return ("CreateSite");
	}
	

	@RequestMapping(value="/CreateSite", method=RequestMethod.POST)
    public String handleSiteForm(@ModelAttribute Site siteObject) {
		
		log.info("In CreateSite POST");
		log.info("received values" + siteObject.toString());
		String statusMessage="";
		
		siteObject.setSiteId(0);
		siteObject.setCreationDate(new Date());
		siteObject.setCreatedBy(1);
		
		Site rc = new Site();
		try {
			rc = ss.addSite(siteObject);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "CreateSite";
		}
		
		log.info(statusMessage);
		//model.addAttribute("Status", statusMessage);
		//return "ShowStatus";
		 log.info("Site succesfully inserted in database" + rc.getSiteId());
		 String newview = "redirect:/SiteDetail?site="+ rc.getName();
		 log.info(newview);
		return newview;
	}

}

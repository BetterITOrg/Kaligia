/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.betterit.kaligia.KBSystem;
import com.betterit.kaligia.KaligiaRunOrder;
import com.betterit.kaligia.segmentParams;
import com.betterit.kaligia.dao.model.kaligia.Device;
import com.betterit.kaligia.dao.model.kaligia.DeviceInst;
import com.betterit.kaligia.dao.model.kaligia.EndPoint;
import com.betterit.kaligia.dao.model.kaligia.Site;
import com.betterit.kaligia.service.DeviceService;
import com.betterit.kaligia.service.EndPointService;
import com.betterit.kaligia.service.SiteService;


/**
 * @author nayar
 *
 */


@Controller
public class SiteDetailController {

	Logger log = Logger.getLogger(SiteDetailController.class.getName());

	@Autowired
	private SiteService ss;
	
	@Autowired
	private DeviceService deviceServiceObject;
	
	@Autowired
	private EndPointService eps;
	
	@Autowired
	private DeviceService ds;
	
	@RequestMapping(value="/SiteDetail", method=RequestMethod.GET)
    public String kbsForm(Model model) {
		
		List<Site> siteList = ss.findAll();
		List<Device> deviceList = deviceServiceObject.findAll();
		KBSystem kbsObj = new KBSystem();
		List<DeviceInst> dList = new ArrayList<DeviceInst>();
		
		//initialize the list of devices
		for(int i=1; i<8; i++){
			DeviceInst dObj= new DeviceInst();
			dList.add(dObj);
		}
		kbsObj.setKbsDeviceList(dList);
		
		model.addAttribute("SiteList", siteList);
		model.addAttribute("DeviceList", deviceList);
		model.addAttribute("KBSystem", kbsObj);
		return ("SiteDetail");
	}
	
	@RequestMapping(value="/SiteDetail", method=RequestMethod.POST)
	public String createkbs(@ModelAttribute KBSystem kbsObject, Model model) {
	
		log.info("In SiteDetail POST");
		log.info("parameters are " + kbsObject.toString());
		
		EndPoint ep = kbsObject.getEndpoint();
		List<DeviceInst> dil = kbsObject.getKbsDeviceList();
		//Create EndPoint
		eps.createEndPoint(ep);
		
		//Create DeviceInst
		ds.insertDeviceInst(dil);
		
		//Map EndPointDevices
		eps.mapEndPointDevices(ep, dil);
		
	return ("SiteDetail");
	}
}

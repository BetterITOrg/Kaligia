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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.betterit.kaligia.DeviceList;
import com.betterit.kaligia.KBSystem;
import com.betterit.kaligia.dao.model.kaligia.Device;
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
@PreAuthorize("hasAnyRole('ROLE_Admin')")
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
    public String kbsForm(@RequestParam(value="siteId", defaultValue="0") int siteId, Model model) {
		
		List<Site> siteList = ss.findAll();
		List<Device> deviceList = deviceServiceObject.findAll();
		KBSystem kbsObj = new KBSystem();
		List<DeviceList> dList = new ArrayList<DeviceList>();
		
		if(siteId !=0)
		{
			kbsObj=eps.getEndPointDetails(siteId);
		}
		//initialize the list of devices
		if (kbsObj==null)
		{
			kbsObj=new KBSystem();
			for(int i=1; i<9; i++){
				DeviceList dObj= new DeviceList();
				dList.add(dObj);
			}
			kbsObj.setKbsDeviceList(dList);
		}
		
		model.addAttribute("SiteList", siteList);
		model.addAttribute("DeviceList", deviceList);
		model.addAttribute("KBSystem", kbsObj);
		return ("SiteDetail");
	}
	
	@RequestMapping(value="/SiteDetail", method=RequestMethod.POST)
	public String createkbs(@ModelAttribute KBSystem kbsObject, Model model) {
	
		log.info("In SiteDetail POST");
		log.info("parameters are " + kbsObject.toString());
		log.info("list length is " + kbsObject.getKbsDeviceList().size());
		
		//Set the creation parameters
		String epLower= kbsObject.getEndpoint().getName().toLowerCase();
		kbsObject.getEndpoint().setName(epLower);
		kbsObject.getEndpoint().setCreationDate(new Date());
		kbsObject.getEndpoint().setCreatedBy(1);
		
		
		Iterator<DeviceList> dlIterator = kbsObject.getKbsDeviceList().iterator();
		while (dlIterator.hasNext()) {
			DeviceList paramObject= dlIterator.next();
			paramObject.getDiObj().setCreationDate(new Date());
			paramObject.getDiObj().setCreatedBy(1);
			paramObject.getDiObj().setStatus("ACTIVE");
			log.info("device inst list is " + paramObject.toString());
				
		}
		
		EndPoint ep = kbsObject.getEndpoint();
		List<DeviceList> dil = kbsObject.getKbsDeviceList();
		
		//Create EndPoint
		boolean deleteAdd = eps.createEndPoint(ep);
		
		//Create DeviceInst
		ds.insertDeviceInst(dil);
		
		//Map EndPointDevices
		eps.mapEndPointDevices(ep, dil, deleteAdd);
		String retView= "redirect:/KBSDetail?siteId=" + kbsObject.getEndpoint().getSiteId();
	//return ("redirect:/KBSDetail");
		return retView;
	}
	
	@RequestMapping(value="/KBSDetail", method=RequestMethod.GET)
    public String kbsDetailForm(@RequestParam(value="siteId", defaultValue="0") int siteId, Model model) {
		log.info("In KBSDetail GET with siteId " + siteId);
		List<Site> siteList = ss.findAll();
		List<Device> deviceList = deviceServiceObject.findAll();
		KBSystem kbsObj = new KBSystem();
		List<DeviceList> dList = new ArrayList<DeviceList>();
		
		if(siteId !=0)
		{
			kbsObj=eps.getEndPointDetails(siteId);
		}
		//initialize the list of devices
		if ((kbsObj==null) || (siteId==0))
		{//initialize object and pass the siteid back to the page.
			EndPoint epObj=new EndPoint();
			kbsObj=new KBSystem();
			epObj.setEndPointId(0);
			epObj.setSiteId(siteId);
			kbsObj.setEndpoint(epObj);
			for(int i=1; i<9; i++){
				DeviceList dObj= new DeviceList();
				dList.add(dObj);
			}
			kbsObj.setKbsDeviceList(dList);
		}
		
		model.addAttribute("SiteList", siteList);
		model.addAttribute("DeviceList", deviceList);
		model.addAttribute("KBSystem", kbsObj);
		return ("KBSDetail");
	}
}

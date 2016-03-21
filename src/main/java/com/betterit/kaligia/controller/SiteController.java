/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betterit.kaligia.dao.model.kaligia.Site;
import com.betterit.kaligia.service.SiteService;

/**
 * @author nayar
 *
 */

 @RestController
public class SiteController {

	Logger log = Logger.getLogger(SiteController.class.getName());

	@Autowired
	private SiteService ss;

	@RequestMapping(value="/getSiteDetails", method=RequestMethod.GET)
    public List<Site> getSiteDetails(@RequestParam(value="siteName", defaultValue="ALL")String siten) {

		log.info(" In getSiteDetails with " + siten);
		List<Site> siteList = new ArrayList<Site>();
		
		if (siten.equals("ALL")){
			siteList = ss.findAll();
		}
		else 
		{
			siteList = ss.getSiteByName(siten);
		}
		log.info("returned list is " + siteList.size());
		
		/*model.addAttribute("DeviceList", deviceList);
		model.addAttribute("endPoint", createSiteObj);*/
				
		return siteList;
	}


}

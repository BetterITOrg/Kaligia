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

import com.betterit.kaligia.CameraControl;
import com.betterit.kaligia.SkinCapture;

/**
 * @author nayar
 *
 */

 @RestController
public class CameraController {
	 static { 
	      System.setProperty("java.awt.headless", "false");
	      System.out.println(java.awt.GraphicsEnvironment.isHeadless());
	      
	    }
	Logger log = Logger.getLogger(CameraController.class.getName());

	//@Autowired
	//private AppConfigService appConfigService;

	@RequestMapping(value="/skinCapture", method=RequestMethod.GET)
    public SkinCapture runSkinCapture(@RequestParam(value="orderNo", defaultValue="Dummy1")String orderNo) {

		log.info(" In runSkinCapture for " + orderNo);
		//TODO read dierctory from config table IMAGE_FILE_DIRECTORY
		//List<AppConfig> siteList = new ArrayList<AppConfig>();
		// appConfigService.getnameConfig("IMAGE_FILE_DIRECTORY", "VALID");
		// 
		String imageDir = "c:/Kaligia/data/";
		SkinCapture restObj= new SkinCapture();
		
		
		if (orderNo.equals("Dummy1"))
				{
			restObj.setImageFile(imageDir+orderNo);
			return restObj;
				}
		CameraControl ccObj = new CameraControl();
		try{
			restObj = ccObj.capture(imageDir+orderNo);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
		log.info("returned camera params are : " + restObj.toString());
		
		/*model.addAttribute("DeviceList", deviceList);
		model.addAttribute("endPoint", createSiteObj);*/
				
		return restObj;
	}


}

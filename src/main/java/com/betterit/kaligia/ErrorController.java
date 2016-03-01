/**
 * 
 */
package com.betterit.kaligia;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author V135012
 *
 */
@Controller
public class ErrorController {
	
	Logger log = Logger.getLogger(ErrorController.class.getName());
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
    public String errorHandler(Model model) {
		
		log.info("System Error");
		return ("error");
	}

}

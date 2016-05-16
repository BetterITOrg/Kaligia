/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.service.TestProcedureService;


/**
 * @author nayar
 *
 */


@Controller
public class KaligiaMainController {

	Logger log = Logger.getLogger(KaligiaMainController.class.getName());

	@Autowired
	private TestProcedureService procedureServiceObject;
	
	@RequestMapping(value="/KaligiaMainApp", method=RequestMethod.GET)
    public String mainForm(HttpSession session, HttpServletRequest request, Model model) {
		
		if (request.isUserInRole("ROLE_Operator")) {
	        return ("redirect:/KaligiaRunOrder");
	    }
		/** TO-DO get all procedures from database */
		List<TestProcedure> procedureList = procedureServiceObject.findAll();
		
		model.addAttribute("ProcedureList", procedureList);
		
		return ("KaligiaMainApp");
	}
}

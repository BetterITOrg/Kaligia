/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
public class loginController {

	Logger log = Logger.getLogger(loginController.class.getName());

		
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest req) {
        log.info("login: " + req);
        return "login";
    }
}

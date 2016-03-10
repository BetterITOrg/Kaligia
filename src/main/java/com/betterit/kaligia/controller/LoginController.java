/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author nayar
 *
 */


@Controller
public class LoginController {

	Logger log = Logger.getLogger(LoginController.class.getName());

		
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest req) {
        log.info("login: " + req);
        return "login";
    }
}

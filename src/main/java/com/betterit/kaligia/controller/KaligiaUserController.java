/**
 * 
 */
package com.betterit.kaligia.controller;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.betterit.kaligia.ProcedureDetail;
import com.betterit.kaligia.dao.model.kaligia.Roles;
import com.betterit.kaligia.dao.model.kaligia.Users;
import com.betterit.kaligia.service.UsersService;


/**
 * @author nayar
 *
 */


@Controller
public class KaligiaUserController {

	Logger log = Logger.getLogger(KaligiaUserController.class.getName());

	@Autowired
	private UsersService usObj;
	
	@RequestMapping(value="/KaligiaUserApp", method=RequestMethod.GET)
    public String userForm(Model model) {
		
		/** TO-DO get all procedures from database */
		List<Users> uList = usObj.findAll();
		
		model.addAttribute("UserList", uList);
		
		return ("KaligiaUserApp");
	}
	
	@RequestMapping(value="/getUserDetail", method=RequestMethod.GET)
    public String userDetailsForm(@RequestParam(value="usrId" , defaultValue="0") int usr_id, Model model) {
		Users userObj= new Users();
		log.info("In userDEtails GET with tpsid" + usr_id);
		if(usr_id ==0)
			return "UserDtails";
		
		try{
			
			userObj=usObj.getUser(usr_id);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String statusMessage="failed to find user for id " + usr_id;
			log.info(statusMessage);
			model.addAttribute("Status", statusMessage);
			return "ShowStatus";
		}
		
		log.info("found user Details with userid " + usr_id);
		log.info(userObj.toString());
		
		List<Roles> rList = usObj.getAllRoles();
		model.addAttribute("RoleList", rList);
		model.addAttribute("UserDetails", userObj);
				
		return ("UserDetails");
	}
	
	@RequestMapping(value="/UpdateUser", method=RequestMethod.POST)
	public String updateUserHandler(@ModelAttribute Users userObject, Model model){
	
		int rc=0;
		
		log.info("In UpdateUser POST "+ userObject.toString());
		
		try{
			rc=usObj.updateUser(userObject);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String retView= "getUserDetail?usrId="+userObject.getUserId();
		return "getUserDetail";
	}
}

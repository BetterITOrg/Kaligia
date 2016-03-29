/**
 * 
 */
package com.betterit.kaligia.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.betterit.kaligia.UserView;
import com.betterit.kaligia.dao.model.kaligia.Roles;
import com.betterit.kaligia.dao.model.kaligia.Users;
import com.betterit.kaligia.service.UsersService;


/**
 * @author nayar
 *
 */


@Controller
@PreAuthorize("hasAnyRole('ROLE_Admin')")
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
	
	public void encodePassword(Users uObj)
	{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(uObj.getPasswd());
		log.info("original pwd: " + uObj.getPasswd());
		log.info("hashed pwd: " + hashedPassword);
		uObj.setPasswd(hashedPassword);
	}
	
	
	@RequestMapping(value="/getUserDetail", method=RequestMethod.GET)
    public String userDetailsForm(@RequestParam(value="usrId" , defaultValue="0") int usr_id, Model model) {
		log.info("In userDEtails GET with tpsid" + usr_id);
		Users userObj= new Users();
		UserView uservObj = new UserView();
		List<Roles> rList = usObj.getAllRoles();
		
		if(usr_id ==0){
			model.addAttribute("RoleList", rList);
			model.addAttribute("UserDetails", uservObj);
			return "UserDetails";
		}
		
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
		//populate User View object with dates and users object
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		uservObj.setUserObject(userObj);
		uservObj.setStartDate(formatter.format(userObj.getStartDate()));
		uservObj.setEndDate(formatter.format(userObj.getEndDate()));
		
		
		model.addAttribute("RoleList", rList);
		model.addAttribute("UserDetails", uservObj);
				
		return ("UserDetails");
	}
	
	@RequestMapping(value="/getUserDetail", method=RequestMethod.POST)
	public String updateUserHandler(@ModelAttribute UserView uservObject, Model model){
	
		log.info("In UpdateUser POST "+ uservObject.toString());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			uservObject.getUserObject().setEndDate(formatter.parse(uservObject.getEndDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uservObject.getUserObject().setEndDate(null); //Set default date
		}
		
		try{
			uservObject.getUserObject().setStartDate(formatter.parse(uservObject.getStartDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uservObject.getUserObject().setStartDate(null); //Set default date
		}
		if (uservObject.getUserObject().getPasswd().length() <17)
		{
			log.info("changing password");
			encodePassword(uservObject.getUserObject());
		}
		try{
			log.info("ready to update user : " + uservObject.getUserObject().toString());
			int rc=usObj.updateUser(uservObject.getUserObject());
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String retView= "redirect:/getUserDetail?usrId=" + uservObject.getUserObject().getUserId();
		//return "redirect:/getUserDetail";
		return retView;
	}
	
	
	
	
	@RequestMapping(value="/CreateUser", method=RequestMethod.POST)
	public String createUserHandler(@ModelAttribute UserView uservObject, Model model){
	
		int rc=0;
		
		log.info("In CreateUser POST "+ uservObject.toString());
		
		//Date tempDate=new Date();
		//log.info("tempDate is initialized to " + tempDate.toString());
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date tempDate=formatter.parse(uservObject.getEndDate());

			log.info("uservObject.getEndDate() is" + uservObject.getEndDate());
			log.info("tempDate is "+ tempDate.toString());
			uservObject.getUserObject().setEndDate(tempDate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uservObject.getUserObject().setEndDate(null); //Set default date
		}
		
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date tempDate=formatter.parse(uservObject.getStartDate());
			log.info("uservObject.getStartDate() is" + uservObject.getStartDate());
			log.info("tempDate is "+ tempDate.toString());
			uservObject.getUserObject().setStartDate(tempDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uservObject.getUserObject().setStartDate(null); //Set default date
		}
		encodePassword(uservObject.getUserObject());
		try{
			rc=usObj.insertUser(uservObject.getUserObject()); 
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String retView= "getUserDetail?usrId="+userObject.getUserId();
		return "redirect:/KaligiaUserApp";
	}
	
	@RequestMapping(value="/CreateUser", method=RequestMethod.GET)
    public String createUserForm(Model model) {
		UserView uvObj = new UserView();
		Users userObj= new Users();
		uvObj.setUserObject(userObj);
		log.info("In CreateUser GET with tpsid");
			
				
		List<Roles> rList = usObj.getAllRoles();
		model.addAttribute("RoleList", rList);
		model.addAttribute("UserDetails", uvObj);
				
		return ("CreateUser");
	}
}

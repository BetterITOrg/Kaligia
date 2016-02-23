/**
 * 
 */
package com.betterit.kaligia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.dao.model.kaligia.Users;
import com.betterit.kaligia.dao.repository.kaligia.UsersMapper;

/**
 * @author V135012
 *
 */
@Service
public class UsersService {
	
	@Autowired
	private UsersMapper userMapper;
	
	private static final Logger log = LoggerFactory.getLogger(UsersService.class);
	
	public Users getUser(int userID) {
		log.info("User ID: " + userID);
		return userMapper.selectByPrimaryKey((Integer)userID);
	}
	
	public int insertUser(Users user) {
		log.info("Inserting User Name :" + user.getName());
		
		int rc;
		try {
			rc = userMapper.insert(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("User inser failed : " + e.getMessage());
			return 100;
		}
		
		return rc;
	}

}

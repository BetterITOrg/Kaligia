/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betterit.kaligia.dao.model.kaligia.Roles;
import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.dao.model.kaligia.Users;
import com.betterit.kaligia.dao.model.kaligia.UsersExample;
import com.betterit.kaligia.dao.repository.kaligia.RolesMapper;
import com.betterit.kaligia.dao.repository.kaligia.UsersMapper;

/**
 * @author V135012
 *
 */
@Service
public class UsersService {
	
	@Autowired
	private UsersMapper userMapper;
	
	@Autowired 
	private RolesMapper roleMapper;
	
	private static final Logger log = LoggerFactory.getLogger(UsersService.class);
	
	public Users getUser(int userID) {
		log.info("User ID: " + userID);
		return userMapper.selectByPrimaryKey((Integer)userID);
	}
	
	public Users getUserByName(String Name) {
		if(Name==null || Name=="") {
			Name="olesia";
		}
		UsersExample ue = new UsersExample();
		ue.createCriteria().andLoginIdEqualTo(Name);
		List<Users> users=userMapper.selectByExample(ue);
		if(users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
	
	public int insertUser(Users user) {
		log.info("Inserting User Name :" + user.getLoginId());
		
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
	
	public Roles getUserRoles(Integer role_id) {
		return roleMapper.selectByPrimaryKey(role_id);
	}
	
	public int updateUser(Users user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}
	
	public List<Users> findAll() {
		
		List<Users> userl = userMapper.selectByExample(null);
		return userl;
		
	}
	
	public List<Roles> getAllRoles() {
		
		List<Roles> roleList = roleMapper.selectByExample(null);
		return roleList;
		
	}

}

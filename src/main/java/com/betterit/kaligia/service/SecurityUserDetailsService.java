/**
 * 
 */
package com.betterit.kaligia.service;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.betterit.kaligia.dao.model.kaligia.Users;


/**
 * @author V135012
 *
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityUserDetailsService.class);
	private UsersService userService;

    @Autowired
    public SecurityUserDetailsService(UsersService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String loginID) throws UsernameNotFoundException {
    	
    	boolean accountNonExpired=true;

        if (loginID == null || loginID.isEmpty())
        {
                log.error("User name is empty");
                throw new UsernameNotFoundException("Could not find the user '"+ loginID + "'");
        }

        Users user = userService.getUserByName(loginID);
        if(user!=null) {
            Date now = new Date();

            if (user.getStartDate() != null)
            {
                    if (user.getStartDate().after(now))
                    {
                            accountNonExpired=false;
                    }
            }

            if (user.getEndDate() != null)
            {
                    if (now.after(user.getEndDate()))
                    {
                            accountNonExpired=false;
                    }
            }
            
            log.debug("User" + loginID);
            return new User(loginID,user.getPasswd(), true, accountNonExpired, true, true, getGrantedAuthorities(user.getRoleId()));


        } else {
        	
        	throw new UsernameNotFoundException(String.format("User with login=%s was not found", loginID));
        
        }
        
    }
    
    private Collection<GrantedAuthority> getGrantedAuthorities(Integer role_id) {
    	String authList="ROLE_" + userService.getUserRoles(role_id).getName();
        log.debug("For user authList:{}",authList);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authList);
    }

}

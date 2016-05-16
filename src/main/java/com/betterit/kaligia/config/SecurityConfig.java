/**
 * 
 */
package com.betterit.kaligia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.betterit.kaligia.service.SecurityUserDetailsService;



/**
 * @author V135012
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.headers().frameOptions().sameOrigin();
		http
		.headers()
		.frameOptions()
		.sameOrigin()
		.and()
		.authorizeRequests()
		.antMatchers("/images/*", "/getSiteDetails").permitAll()
		.anyRequest().fullyAuthenticated()
		.and()
		 .formLogin()
         .loginPage("/login")
         .defaultSuccessUrl("/KaligiaMainApp.html", true)
         .permitAll()
         .and()
         .logout()
         .permitAll();
		
		//http.httpBasic();
		//http.csrf().disable();
	}

		
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
    }   
}

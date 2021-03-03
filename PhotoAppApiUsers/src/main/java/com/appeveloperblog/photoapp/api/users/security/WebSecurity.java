package com.appeveloperblog.photoapp.api.users.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appeveloperblog.photoapp.api.users.service.UsersService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private Environment env;
	private UsersService usersService;
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	public WebSecurity(Environment env,UsersService usersService,BCryptPasswordEncoder bcryptPasswordEncoder) {

		this.env = env;
		this.usersService=usersService;
		this.bcryptPasswordEncoder=bcryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip")).and()
				.addFilter(getAuthenticationFilter());

		http.headers().frameOptions().disable();
	}

	private Filter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationfilter = new AuthenticationFilter(usersService,env,authenticationManager());
		//authenticationfilter.setAuthenticationManager(authenticationManager());
		// TODO Auto-generated method stub
		authenticationfilter.setFilterProcessesUrl(env.getProperty("login.url.path"));
		return authenticationfilter;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception
	{
		auth.userDetailsService(usersService).passwordEncoder(bcryptPasswordEncoder);
		
		
		
		
	}

}

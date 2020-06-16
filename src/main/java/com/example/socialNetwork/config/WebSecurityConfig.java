package com.example.socialNetwork.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import com.example.socialNetwork.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private UserService userService;
	@Override
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
			.authorizeRequests()
				.antMatchers("/", "/loginrest", "/changepass/*", "/changepassword", "/newuser", "/sendemail",
						"/uploudhoto", "/testauth", "/activate/*", "/images/*", "/temp/**", "/css/*").permitAll()
				.anyRequest().authenticated()
				.and()
			.logout()
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
				.logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
				    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
				});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	} 
}
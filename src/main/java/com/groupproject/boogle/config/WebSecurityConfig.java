package com.groupproject.boogle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.groupproject.boogle.handler.MyAuthenticationFailureHandler;
import com.groupproject.boogle.model.CustomUserDetails;
import com.groupproject.boogle.service.CustomUserDetailsService;

@Configuration // this annotation indicates that this class is a configuration class.
@EnableWebSecurity // with @configuration and this annotation, it will allow us to configure the web security settings
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// @Autowired
	// private DataSource dataSource;
	
	/** expose a custom UserDetailsService as a bean **/
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	/** password encoder **/
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/** retrieve user information from a database for UserDetailsService **/
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedDoubleSlash(true);
	    return firewall;
	}
	
	@Bean
	public UserDetails userDetails() {
		return new CustomUserDetails();
	}
	
	@Bean
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
	    return new MyAuthenticationFailureHandler();
	}

	/** get the user information from Authentication token and DaoAuthenticationProvider
	 * and then, it determines whether the informations are matched or not **/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/account").authenticated() // only authenticated() user can access to the account page
			.anyRequest().permitAll()  // any request is accepted for all the pages except for the account page
			.and()
			.formLogin()
				.loginPage("/login")  // use a custom login page
				.usernameParameter("email") // email as a parameter to log in
				.passwordParameter("password")  // password parameter to log in
				.defaultSuccessUrl("/home")  // when a login is successful, show the home page
				.failureHandler(getAuthenticationFailureHandler())
				.permitAll()
			.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/login"); // when a user log out, show the login page
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	}
	
	
	
	
}

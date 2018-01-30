package com.apps.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.apps.user.MyDBAuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired(required=true)
	MyDBAuthenticationService myDBAuthenticationService;

	@Autowired
	public void configureGlobel(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(myDBAuthenticationService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		// The pages requires login as EMPLOYEE or MANAGER.
		// If no login, it will redirect to /login page.
		http.authorizeRequests()
				.antMatchers("/orderList.htm", "/order.htm", "/accountInfo.htm")//
				.access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER')");

		// For MANAGER only.
		http.authorizeRequests().antMatchers("/product.htm")
				.access("hasRole('ROLE_MANAGER')");

		// When the user has logged in as XX.
		// But access a page that requires role YY,
		// AccessDeniedException will throw.
		http.authorizeRequests().and().exceptionHandling()
				.accessDeniedPage("/403");

		// Config for Login Form
		http.authorizeRequests().and().formLogin()
		// Submit URL of login page.
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login.htm")//
				.defaultSuccessUrl("/accountInfo.htm")//
				.failureUrl("/login.htm?error=true")//
				.usernameParameter("userName")//
				.passwordParameter("password")
				// Config for Logout Page
				// (Go to home page).
				.and().logout().logoutUrl("/logout.htm").logoutSuccessUrl("/");

	}
}

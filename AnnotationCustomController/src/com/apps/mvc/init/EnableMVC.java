package com.apps.mvc.init;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.apps.controller.AppController")
public class EnableMVC {

	public EnableMVC() {
		System.out.println("WEB Enable class");
	}
	
}

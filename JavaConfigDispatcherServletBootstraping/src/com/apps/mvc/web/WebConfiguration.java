package com.apps.mvc.web;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebConfiguration {
	
	@Bean
	public HandlerMapping SimpleUrlHandlerMappingbean() {
		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
		Properties prop = new Properties();
		prop.put("vission.htm", "vissionmvc");
		mapping.setMappings(prop);
		return mapping;
	}

	@Bean(name = "vissionmvc")
	public ParameterizableViewController getController() {
		ParameterizableViewController controller = new ParameterizableViewController();
		controller.setViewName("VissionView");
		return controller;
	}

	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}

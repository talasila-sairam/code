package com.apps.mvc.init;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import com.apps.mvc.web.WebConfiguration;

public class ApplicationInitializer extends AbstractDispatcherServletInitializer{

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		AnnotationConfigWebApplicationContext  webContext=new AnnotationConfigWebApplicationContext();
		webContext.register(WebConfiguration.class);
		return webContext;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"*.htm"};
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
	AnnotationConfigWebApplicationContext  rootContext=new AnnotationConfigWebApplicationContext();
		return rootContext;
	}

}

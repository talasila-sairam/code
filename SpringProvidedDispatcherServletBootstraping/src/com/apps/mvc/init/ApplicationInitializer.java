package com.apps.mvc.init;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractDispatcherServletInitializer{

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		XmlWebApplicationContext webContext=new XmlWebApplicationContext();
		webContext.setConfigLocation("WEB-INF\\Application-beans.xml");
		return webContext;
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"*.htm"};
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		XmlWebApplicationContext rootContext=new XmlWebApplicationContext();
		rootContext.setConfigLocation("WEB-INF\\dispatcher-servlet.xml");
		return rootContext;
	}

}

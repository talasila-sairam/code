package com.apps.webinit;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class JavaWebApplicationInitializer implements org.springframework.web.WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext sContext) throws ServletException {
		XmlWebApplicationContext rootContext=null;
		XmlWebApplicationContext webContext=null;
		DispatcherServlet dispatcher=null;
		ContextLoaderListener listener=null;
		
		rootContext=new XmlWebApplicationContext();
		rootContext.setConfigLocation("WEB-INF/Application-beans.xml");
		webContext=new XmlWebApplicationContext();
		webContext.setConfigLocation("WEB-INF/dispatcher-servlet.xml");
		listener=new ContextLoaderListener(rootContext);
		sContext.addListener(listener);
		dispatcher=new DispatcherServlet(webContext);
		ServletRegistration.Dynamic dynamic=sContext.addServlet("dispatcher", dispatcher);
		dynamic.addMapping("*.htm");
		dynamic.setLoadOnStartup(2);
	}

}

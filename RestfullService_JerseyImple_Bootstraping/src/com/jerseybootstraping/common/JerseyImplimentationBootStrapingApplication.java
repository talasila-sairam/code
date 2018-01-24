package com.jerseybootstraping.common;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.jerseybootstraping.resource.PaytmResource;
import com.jerseybootstraping.resource.Product;

/*This is the Third Bootstraping option that is available as part of Jersey Implimentation. In genral to acess by web component 
 * through network. i need adress.so if i send the request to a servlet Container will get the request and it will forwords the 
 * request to coressponding url(based on the address). This is done by Underlying servlet container. this implimentation has 
 * provided by servlet API Implimentation vendors. At the time of first request. or at the time of deployment time it will fetch 
 * the metadata of the components either form annotations or xml will be placed inside the metadata of the servlert container
 * so if i dont want to use web.xml and and i dont have source code to write annotations as part of annotations but either of 
 * support is not there in such a case i have to useProgrametic provided by Servlet Api as part of it you write a class that 
 * implements from ServletContainerIntializer and over ride one method called onStartup() method this method will be called just 
 * after intializing the servletContext then this will call onStartup method and it will read the data and placed inside the metadata
 * of the servlet context this is how it will work.and we need to register our servlet with servletcontainer in programetic way
 * This is the logic that every one need to write the people that want to work with annotations but source code is not there with us.
 * in such a case jersey come up with unique implimentation you no need to write anything this is the boilerplate logic that every one 
 * need to write as part of your application devlopment so write just write one class that extends from Application and make that 
 * class annotated with @ApplicationPath so i only will be create the class and i only register your resources with ServletContainer
 *  this is how this approach will work
 *  
 *  Test Code that Jersey People provided as part of Default Implementation
 *  class JerseyServletContainerIntializer implemets ServletContainerIntializer{
 *    //it is a contract method comming from servletApi and ContextualDependency Lookup
 *    public object onStartup(ServletContext servletContext){
 *    servletContext.add(PaytmApplication.class);
 *    //From here it will get the url pattern and some other information
 *    register("servletName" ,class(org.glassfish.jersey.servlet.ServletContainer,url(comming from @ApplicationPath annotation)));
 *    }
 *  } 
 *  If you write a class that extends from Application and provided with @ApplicationPath this will happen internally this every 
 *  thing will be taken care by JerseyImpl
 *  */



public class JerseyImplimentationBootStrapingApplication extends Application {
	protected Set<Object> singletons;
	protected Set<Class<?>> getClasses;
	 public JerseyImplimentationBootStrapingApplication() {
		 System.out.println("inside the Application class constructor");
		 singletons = new HashSet<Object>();
		 getClasses = new HashSet<Class<?>>();
	}
	 /*This method is used to make our wished resource classes as singletons. here we have to create those objects and we have to give this to servletcontainer
	  * so that servlet container will not create more objects to it.at the time of deployment it will call and placed inside the metadata of the ServletContainer Engine*/
	 
	 @Override
	 public Set<Object> getSingletons() {
		 System.out.println("inside getSingletons method");
		 singletons.add(new PaytmResource());
		return singletons;
	}
	 
	 /*This method is used to make oue wished classes as request scoped or non single ton objects for every request it will create one object
	  *  to that particular resource class. here we have to provide the names so that it will crearte objects by it self*/
	 @Override
	public Set<Class<?>> getClasses() {
		 System.out.println("inside getClasses method");
		 getClasses.add(Product.class);
		 return getClasses;
	}
}

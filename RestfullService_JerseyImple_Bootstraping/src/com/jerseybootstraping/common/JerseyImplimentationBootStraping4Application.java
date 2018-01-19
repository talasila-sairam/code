package com.jerseybootstraping.common;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*This is also same as 4th Bootsraping option here we not over rideing any methods that are comming from Application class
 * So by default it will create the resource classes as packages as *.* and even sub pacakages also it will intialized
 * but we have to annotate this class with @ApplicationPath("/something") so that ServletContainer(JerseyImpl) will identified
 * */
//@ApplicationPath("/common/*")
public class JerseyImplimentationBootStraping4Application extends Application {
	
}

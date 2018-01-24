package com.jerseybootstraping.common;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import com.jerseybootstraping.resource.PaytmResource;
import com.jerseybootstraping.resource.Product;

/*This is the child class of Application class if you extend from this class it has provided some set of lines of boiler plate code that
 * every one has to write in their classess.in genral every one will use this class only and it is standard one*/
@ApplicationPath("/common/*")
public class JerseyBootStrapingResourceConfig extends ResourceConfig{
public JerseyBootStrapingResourceConfig() {
	System.out.println("inside the ResourceConfig class");
	register(Product.class);
	register(new PaytmResource());
}
}

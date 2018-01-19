package com.jerseybootstraping.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/*For Every Resource class is identified by this name so it is manadatory.This will be identified by the Annotation called Path we have to provide the 
 * url inside this annotation This Annotation is called RootResourceClass*/
@Path("/paytm")
public class PaytmResource {

	 public PaytmResource() {
		System.out.println("inside the Paytm Resource Constructor");
	}
	 /*This is part of the Second Architecture principle Every method inside the class should be binded with the http centric methods
	   This is called Http Method Binding
	   This Annotation is used to provide the response type means what we are producing
	   This QueryParam Annotation is used to get the QueryParameter values sent by client as part of the request. Here ServletContainer will read those values from
	   Request Parameter and it will bind to this.So here by using QueryParameter we can get those values to our resource class*/
	 
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public int getBalance(@QueryParam("mobile")String mobile) {
		System.out.println("inside paytm class method");
		return this.hashCode();
	}
}

package com.jerseybootstraping.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/product")
public class Product {

	public Product() {
		System.out.println("inside the Product class constructor");
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public int getProductPrise(@QueryParam("productId")String productId) {
		System.out.println("inside the getProductPrise method");
		return this.hashCode();
	}
}

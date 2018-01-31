package com.apps.controller;

import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;

import com.apps.dto.Company;
import com.apps.dto.CompanyInput;

@Path("/check/{loc}")
public class RedBricks {
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{loc}")
	public String getPlots(@PathParam("loc") String loc) {
		return "<name>" + loc + "</name>";
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String getPlotsResource(@PathParam("loc") String loc) {
		return "<name>" + loc + "</name>";
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/getplot")
	public String getPlotsResources(@QueryParam("location") String loc) {
		return "<name>" + loc + " with query param</name>";
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{loc}.{dis}")
	public String getPlotsDistance(@PathParam("loc") String loc, @PathParam("dis") String distance) {
		System.out.println(loc + "    " + distance);

		return "<data><km>" + loc + "</km><m>" + distance + "</m></data>";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/matrix/{location}")
	public String matrixParamResource(@PathParam("location") String loc, @MatrixParam("arearsize") String areaSize,
			@MatrixParam("type") String type) {
		return "====" + loc + "===" + areaSize + "====" + type;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/chapters")
	public String getListOfBooks(@HeaderParam("User-Agent") String acceptLanguage,
			@CookieParam("user-lang") String userAgent) {
		return "Header=========" + acceptLanguage + "Cookie===============" + userAgent;
	}

	@POST
	@Path("/formParams")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String formParamTest(@FormParam("name") String name, @FormParam("age") String age) {
		return "<form><name>" + name + "</name><age>" + age + "</age></form>";
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/xmlobject")
	@Produces(MediaType.APPLICATION_XML)
	public Company getCompanyStatus(CompanyInput cmpyInput) {
		System.out.println("Received inputs are:::::::::::" + cmpyInput.toString());
		Company company = new Company();
		company.setCmpyName("Apps");
		company.setCmpyLocation("Hyderabad");
		company.setRevenue(12000000);
		return company;
	}
	@GET
	@Path("/{/bookCode}")
	public void getAsyncData(@PathParam("bookCode") String productCode, @Suspended AsyncResponse asyncResponse) {
		String result= "This is asynchronous response.";
		asyncResponse.resume(result);
		
	}
}

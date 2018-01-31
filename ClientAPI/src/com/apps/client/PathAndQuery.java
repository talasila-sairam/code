package com.apps.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebEndpoint;

import com.apps.dto.Company;
import com.apps.dto.CompanyInput;

public class PathAndQuery {
	public static void main(String[] args) {
		String location = PathAndQuery.getPathParamLocation("Hyderabad", "Ameerpet");
		System.out.println("PATH PARMS==========" + location);

		String locationName = PathAndQuery.getQueryParamLocation("Hyderabad", "Ameerpet");
		System.out.println("QUERY PARAMS==========" + locationName);

		String matrixResponse = PathAndQuery.getMatrixParam("Hyderabad", 10024, "Land");
		System.out.println("MATRIX PARAM======" + matrixResponse);

		String headerAndCookie = PathAndQuery.getHeaderANdCookie("English", "Chandu");
		System.out.println("HEADER AND COOKIE" + headerAndCookie);

		Response formParams = PathAndQuery.getFormParams("Chandu", 24);
		System.out.println("FORM PARAMS are=============" + formParams.readEntity(String.class));
		
		CompanyInput inputs = new CompanyInput();
		inputs.setCmpyLocation("HYDERABAD");
		inputs.setCmpyType("MNC");
		inputs.setLevel(10);
		Company read = PathAndQuery.getXmlReadData(inputs);
		System.out.println("From XML read====== "+read);
		
		Response paramsSync = PathAndQuery.getFormParamsWithSync("Chandu", 24);
	}

	private static Response getFormParamsWithSync(String string, int i) {
		final String BASE_URL ="http://localhost:8081/RestParentScopes/api/check/Hyderabad";
		
		return null;
	}

	private static Company getXmlReadData(CompanyInput inputCompany) {
		final String BASE_URL ="http://localhost:8081/RestParentScopes/api/check/Hyderabad";
		ClientBuilder builder = ClientBuilder.newBuilder();
		Client client = builder.newClient();
		WebTarget target = client.target(BASE_URL).path("/xmlobject");
		Response response = target.request(MediaType.APPLICATION_XML).post(Entity.xml(inputCompany));
		return response.readEntity(Company.class);
	}

	private static Response getFormParams(String name, int age) {
		final String BASE_URL = "http://localhost:8081/RestParentScopes/api/check/";
		ClientBuilder clientBuilder = ClientBuilder.newBuilder();
		Client client = clientBuilder.newClient();
		WebTarget target = client.target(BASE_URL).path("Hyderabad").path("/formParams");
		Form formData=new Form();
		formData.param("name", "chandu");
		formData.param("age", "24");
		Response response = target.request().accept(MediaType.APPLICATION_XML).post(Entity.form(formData));
		return response;
	}

	private static String getHeaderANdCookie(String string1, String string2) {
		final String BASE_URL = "http://localhost:8081/RestParentScopes/api/check/";
		return ClientBuilder.newClient().target(BASE_URL).path("Hyderabad").path("/chapters").request()
				.header("User-Agent", string1).cookie(new Cookie("user-lang", string2)).get(String.class);
	}

	private static String getMatrixParam(String string, int i, String string2) {
		final String BASE_URL = "http://localhost:8081/RestParentScopes/api/check/";
		return ClientBuilder.newClient().target(BASE_URL).path(string).path("/matrix").path(string)
				.matrixParam("arearsize", i).matrixParam("type", string2).request().get(String.class);
	}

	private static String getQueryParamLocation(String string, String string2) {
		final String BASE_URL = "http://localhost:8081/RestParentScopes/api/check/";
		return ClientBuilder.newClient().target(BASE_URL).path(string).path("/getplot").queryParam("location", string2)
				.request().get(String.class);
	}

	private static String getPathParamLocation(String city, String location) {
		final String BASE_URL = "http://localhost:8081/RestParentScopes/api/check/";
		return ClientBuilder.newClient().target(BASE_URL).path(city).path(location).request().get(String.class);
	}
}

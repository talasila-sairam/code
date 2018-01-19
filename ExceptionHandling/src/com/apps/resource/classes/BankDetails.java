package com.apps.resource.classes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app.dto.AccountDetails;
import com.app.dto.Receipt;
import com.apps.mapper.DataNotSufficientException;

@Path("/bank")
public class BankDetails {
	@POST
	@Produces( MediaType.APPLICATION_JSON )
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response withdrowMoney(AccountDetails acc) {
		if (acc.getAccNo() == null || acc.getAccNo().trim().length() == 0) {
			throw new DataNotSufficientException(100, "Provide correct data");
			
		}
		
		Receipt reciept = null;
		reciept = new Receipt(acc.getAccNo(), 10000, 10000 - acc.getAmount());
		return Response.status(200).entity(reciept).build();
	}
}

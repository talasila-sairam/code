package com.apps.mapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccountExceptionMapper implements ExceptionMapper<DataNotSufficientException>{

	@Override
	public Response toResponse(DataNotSufficientException e) {
		return Response.status(406).entity(e).build();
	}

}

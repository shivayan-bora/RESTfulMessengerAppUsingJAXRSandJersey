package org.shivayan.javabrains.messengerapp.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.shivayan.javabrains.messengerapp.model.ErrorMessage;

//@Provider
public class GenericExceptionWrapper implements ExceptionMapper<Throwable>{
	
	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "http://www.google.com");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}
}

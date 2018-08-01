package org.shivayan.javabrains.messengerapp.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("/annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String param,
			@HeaderParam("customHeaderValue") String header, @CookieParam("name") String cookie) {
		return "Hello World! and Matrix param is: " + param + " Header Param is: " + header + " Cookie Param is: "
				+ cookie;
	}

}

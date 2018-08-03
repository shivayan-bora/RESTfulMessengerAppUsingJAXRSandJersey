package org.shivayan.javabrains.messengerapp.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.shivayan.javabrains.messengerapp.model.Message;
import org.shivayan.javabrains.messengerapp.resources.beans.MessageFilterBeans;
import org.shivayan.javabrains.messengerapp.service.MessageService;

/**
 * Root resource (exposed at "messages" path)
 */
// Name of the resource
// It's the URI that maps to this class
@Path("/messages")
public class MessageResource {

	MessageService messageService = new MessageService();

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	// Way to access bean params
	// The HTTP method to be used
	@GET
	// This tells Jersey on what is the return type of the response
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBeans filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() >= 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}

	// Way to access query params
	/*
	 * public List<Message> getMessages(@QueryParam("year") int
	 * year, @QueryParam("start") int start,
	 * 
	 * @QueryParam("size") int size) { if (year > 0) { return
	 * messageService.getAllMessagesForYear(year); } if (start >= 0 && size >= 0) {
	 * return messageService.getAllMessagesPaginated(start, size); } return
	 * messageService.getAllMessages(); }
	 */

	@GET
	// For mapping a subsequent paths
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	// To capture the parameter specified in the path and send it to the constructor
	// of the below method
	public Message getMessage(@PathParam("messageId") long messageId) {
		return messageService.getMessage(messageId);
	}

	@POST
	// This is to tell Jersey what kind of content the service will consume
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		// To send proper status codes in header and the id of the newly created
		// message. '.build()' will create the Response instance using the parameters we
		// have specified (status(Status.CREATED) = For proper status code)
		String id = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(uri).entity(newMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	// This is to tell Jersey what kind of content the service will consume
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	// This is to tell Jersey what kind of content the service will consume
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageService.removeMessage(messageId);
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}

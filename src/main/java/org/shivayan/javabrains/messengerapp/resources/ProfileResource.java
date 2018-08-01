package org.shivayan.javabrains.messengerapp.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.shivayan.javabrains.messengerapp.model.Profile;
import org.shivayan.javabrains.messengerapp.service.ProfileService;

/**
 * Root resource (exposed at "messages" path)
 */
// Name of the resource
// It's the URI that maps to this class
@Path("/profiles")
public class ProfileResource {
	ProfileService profileService = new ProfileService();

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	// The HTTP method to be used
	@GET
	// This tells Jersey on what is the return type of the response
	@Produces(MediaType.APPLICATION_XML)
	public List<Profile> getProfiles() {
		return profileService.getAllProfiles();
	}
	
	@GET
	// For mapping a subsequent paths
	@Path("/{profileName}")
	@Produces(MediaType.APPLICATION_JSON)
	// To capture the parameter specified in the path and send it to the constructor of the below menthod
	public Profile getMessage(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
	}
	
	@POST
	// This is to tell Jersey what kind of content the service will consume
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile addMessage(Profile profile) {
		return profileService.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	// This is to tell Jersey what kind of content the service will consume
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile updateMessage(@PathParam("profileName") String profileName, Profile profile) {
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	// This is to tell Jersey what kind of content the service will consume
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
	}
}

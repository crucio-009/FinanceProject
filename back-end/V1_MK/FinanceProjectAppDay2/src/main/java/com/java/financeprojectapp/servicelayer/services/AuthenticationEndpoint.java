package com.java.financeprojectapp.servicelayer.services;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/authenticate")
public class AuthenticationEndpoint {
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("username") String username, 
            @FormParam("password") String password) {
		try {
		
			// Authenticate the user using the credentials provided
			authenticate(username, password);
			
			// Issue a token for the user
			String token = issueToken(username);
			
			// Return the token on the response
			 // Return the token on the response
            return Response.ok(token).build();
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
        }  
	}

	private String issueToken(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	private void authenticate(String username, String password) {
		// TODO Auto-generated method stub
		
	}
}

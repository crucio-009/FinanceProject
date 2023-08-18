package com.java.financeprojectapp.servicelayer.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.java.financeprojectapp.dataaccess.utility.DataAccessUtility;
import com.java.financeprojectapp.exceptions.DataAccessException;
import com.java.financeprojectapp.servicelayer.entities.TokenManager;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/authenticate")
public class AuthenticationService {
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("username") String username, 
            @FormParam("password") String password) {
		try {
		
			// Authenticate the user using the credentials provided
			authenticate(username, password);
//			System.out.println("Accessing after authentication");
			// Issue a token for the user
//			System.out.println("Before Token Manager");
			TokenManager tokenmanager = new TokenManager();
			String token = tokenmanager.issueToken(username);
//			System.out.println("Accessing after issueToken");
			// Return the token on the response
			 // Return the token on the response
            return Response.ok(token).build();
		} catch (Exception e) {
//			System.out.println("Accessing in Catch Block");
//			e.printStackTrace();
			return Response.status(Response.Status.FORBIDDEN).build();
        }  
	}
	
	private void authenticate(String username, String password) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		String query = null;
		ResultSet resultSet = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "select * from user_credentials where user_id=? and password=?";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			prepstatement.setString(1, username);
			prepstatement.setString(2, password);

			resultSet = prepstatement.executeQuery();
			if (resultSet.next()) {				
				String retrievedUsername = resultSet.getString("user_id");
                String retrievedPassword = resultSet.getString("password");
                
                if (username.equals(retrievedUsername) && password.equals(retrievedPassword)) {
                     System.out.println("Authentication successful.");
                } else {
                    throw new Exception("Invalid credentials.");
                } 
			} else {
                throw new Exception("Invalid credentials.");
            }
		} catch (SQLException e) {
			DataAccessException dataEx = new DataAccessException(e.getMessage(), e);
			throw dataEx;
		} catch (ClassNotFoundException e) {
			DataAccessException dataEx = new DataAccessException(e.getMessage(), e);
			throw dataEx;
		} catch (Exception e) {
			DataAccessException dataEx = new DataAccessException(e.getMessage(), e);
			throw dataEx;
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				DataAccessException dataEx = new DataAccessException(e.getMessage(), e);
				throw dataEx;
			}
		}
	}
}

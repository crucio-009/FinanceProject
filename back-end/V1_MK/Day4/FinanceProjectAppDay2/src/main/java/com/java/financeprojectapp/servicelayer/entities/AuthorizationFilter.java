package com.java.financeprojectapp.servicelayer.entities;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.java.financeprojectapp.dataaccess.utility.DataAccessUtility;
import com.java.financeprojectapp.entities.User;
import com.java.financeprojectapp.exceptions.DataAccessException;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

	@Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the resource class which matches with the requested URL
        // Extract the roles declared by it
    	System.out.println("Hello from Authorization filter");
        Class<?> resourceClass = resourceInfo.getResourceClass();
        List<Role> classRoles = extractRoles(resourceClass);

        // Get the resource method which matches with the requested URL
        // Extract the roles declared by it
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<Role> methodRoles = extractRoles(resourceMethod);

        try {

            // Check if the user is allowed to execute the method
            // The method annotations override the class annotations
            if (methodRoles.isEmpty()) {
                checkPermissions(classRoles, requestContext, true);
            } else {
                checkPermissions(methodRoles, requestContext, false);
            }

        } catch (Exception e) {
        	System.out.println("Here it is aborting");
            requestContext.abortWith(
                Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    // Extract the roles from the annotated element
    private List<Role> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<Role>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<Role>();
            } else {
                Role[] allowedRoles = secured.value();
                System.out.println(Arrays.asList(allowedRoles));
                System.out.println("printing roles aaray");
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private void checkPermissions(List<Role> allowedRoles, ContainerRequestContext requestContext, boolean disregardRoles) throws Exception {
        
    	// Get the Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        System.out.println("Not access from here.");

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader
                            .substring(AUTHENTICATION_SCHEME.length()).trim();
        
    	User authenticatedUser = getAuthenticatedUser(token);;// Get authenticated user information from your authentication mechanism
    	
    	// Check if the user contains one of the allowed roles
        // Throw an Exception if the user has not permission to execute the method
    	if(!disregardRoles) {
    		boolean hasPermission = false;
            for (Role allowedRole : allowedRoles) {
                if (authenticatedUser.getRoles().contains(allowedRole)) {
                    hasPermission = true;
                    break;
                }
            }
            
            if (!hasPermission) {
                throw new Exception("User does not have permission to execute the method");
            }
    	}
    }

    private User getAuthenticatedUser(String token) throws Exception {
    	
        if (token != null && !token.isEmpty()) {
            // Validate the authentication token and retrieve user information
            // Implement your authentication mechanism here, which may involve decoding JWT token, querying a database, etc.
            User user = authenticateAndRetrieveUser(token);
            if (user != null) {
                return user;
            } else {
                throw new Exception("Authentication failed");
            }
        } else {
            throw new Exception("Authentication token not provided");
        }
    }

	private User authenticateAndRetrieveUser(String token) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		String query = null;
		ResultSet resultSet = null;
		User user = null;
		System.out.println("Access from Authorization service");
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "select * from user_credentials where token=?";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			prepstatement.setString(1, token);

			resultSet = prepstatement.executeQuery();
			if (resultSet.next()) {				
				long expirationTime = resultSet.getLong("expiration_time");
		        long currentTime = getCurrentTimeInSeconds();
		        
		        if (currentTime > expirationTime) {
		            throw new Exception("Token has expired");
		        } else {
		        	
		        	String retrievedRole = resultSet.getString("role_id");
	                Set<Role> roles = new HashSet<Role>();
	                switch (retrievedRole) {
					case "0": 
						roles.add(Role.valueOf("CUSTOMER"));
						break;
					case "1":
						roles.add(Role.valueOf("CLERK"));
						break;
					case "2":
						roles.add(Role.valueOf("MANAGER"));
						break;
					}
	                user = new User(resultSet.getString("user_id"), roles);
	                
		        }
		        
			} else {
                throw new Exception("Invalid token");
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
		return user;
	}
	
	private long getCurrentTimeInSeconds() {
		return System.currentTimeMillis() / 1000;
	}
	
	private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                    .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, 
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }
}

package com.java.financeprojectapp.servicelayer.entities;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import com.java.financeprojectapp.dataaccess.utility.DataAccessUtility;
import com.java.financeprojectapp.exceptions.DataAccessException;

public class TokenManager {

	public TokenManager() {
		super();
	}
	
	public String issueToken(String username) throws DataAccessException {
		 // Generate a random token (you can use a library to generate a secure random token)
	    String token = generateRandomToken();
	    long expirationTime = getCurrentTimeInSeconds() + 3600; // 1 hour in seconds
	    // Persist the token along with the user name to a database or storage
	    persistToDatabase(username, token, expirationTime);
//	    System.out.println("Accessing Issue Token");
	    // Return the issued token
	    return token;
	}
	
	public long getCurrentTimeInSeconds() {
		return System.currentTimeMillis() / 1000;
	}

	public void persistToDatabase(String username, String token, long expirationTime) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		String query = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "update user_credentials set token=?, expiration_time=? where user_id=?";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			
			prepstatement.setString(1, token);
			prepstatement.setLong(2, expirationTime);
			prepstatement.setString(3, username);
			
			prepstatement.executeUpdate();
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

	public String generateRandomToken() {
		// Generate a secure random token (you can customize the length and characters as needed)
		SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[32];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
	}
	
	public void validateToken(String token) throws Exception {
	    	
	    	Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			ResultSet resultSet = null;
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
	    }
}

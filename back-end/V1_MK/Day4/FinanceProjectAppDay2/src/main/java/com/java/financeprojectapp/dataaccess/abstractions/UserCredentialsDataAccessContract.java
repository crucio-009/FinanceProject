package com.java.financeprojectapp.dataaccess.abstractions;

import com.java.financeprojectapp.exceptions.DataAccessException;

public interface UserCredentialsDataAccessContract {

	Boolean insert(String username, String password, int role) throws DataAccessException;
	
	Boolean update(String username, String oldPassword, String newPassword, int role) throws DataAccessException;
	
}

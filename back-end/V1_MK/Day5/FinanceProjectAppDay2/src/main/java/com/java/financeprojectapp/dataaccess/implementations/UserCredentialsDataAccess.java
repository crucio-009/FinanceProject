package com.java.financeprojectapp.dataaccess.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.java.financeprojectapp.dataaccess.abstractions.UserCredentialsDataAccessContract;
import com.java.financeprojectapp.dataaccess.utility.DataAccessUtility;
import com.java.financeprojectapp.exceptions.DataAccessException;

public class UserCredentialsDataAccess implements UserCredentialsDataAccessContract {

	@Override
	public Boolean insert(String username, String password, int role) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		String query = null;
		Integer result = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "insert into User_Credentials (user_id, password, role_id) values(?,?,?)";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);

			prepstatement.setString(1, username);
			prepstatement.setString(2, password);
			prepstatement.setInt(3, role);

			result = prepstatement.executeUpdate();
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
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean update(String username, String oldPassword, String newPassword, int role)
			throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		String query = null;
		Integer result = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "update user_credentials set password = ? where user_id = ? and password = ?";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);

			prepstatement.setString(1, newPassword);
			prepstatement.setString(2, username);
			prepstatement.setString(3, oldPassword);

			result = prepstatement.executeUpdate();
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
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

}

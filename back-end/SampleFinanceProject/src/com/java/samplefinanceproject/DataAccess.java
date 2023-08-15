package com.java.samplefinanceproject;


import java.sql.*;

import com.java.samplefinanceproject.entities.Customer;
import com.java.samplefinanceproject.exceptions.DataAccessException;

public class DataAccess {
	
	public Customer fetchById(Integer id) throws DataAccessException {
		if (id > 0) {
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			Customer customer = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
				statement = connection.prepareStatement("select customer_id,customer_name,gender,phone_no,email_id from customers where customer_id=?");
				statement.setInt(1, id);

				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					//System.out.println(resultSet.getString(2));
					
					
					customer = new Customer();
					customer.setCustomerId(resultSet.getInt("customer_id"));
					customer.setCustomerName(resultSet.getString("customer_name"));
					customer.setGender(resultSet.getString("gender"));
					customer.setPhoneNo(resultSet.getString("phone_no"));
					customer.setEmailId(resultSet.getString("email_id"));
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
			return customer;
		} else
			//return null;
			throw new DataAccessException("negative value not allowed");
	}
	
	
}

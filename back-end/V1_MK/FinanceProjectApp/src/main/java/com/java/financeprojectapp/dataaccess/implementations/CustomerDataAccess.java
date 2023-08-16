package com.java.financeprojectapp.dataaccess.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.financeprojectapp.dataaccess.abstractions.CustomerDataAccessContract;
import com.java.financeprojectapp.dataaccess.utility.DataAccessUtility;
import com.java.financeprojectapp.entities.Customer;
import com.java.financeprojectapp.exceptions.DataAccessException;

public class CustomerDataAccess implements CustomerDataAccessContract {

	@Override
	public List<Customer> fetchAll() throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		ResultSet resultSet = null;
		String query = null;
		List<Customer> customers = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "select customer_id,customer_name,gender,phone_no,email_id from customers";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			
			resultSet = prepstatement.executeQuery();
			customers = new ArrayList<Customer>();			
			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(resultSet.getInt("customer_id"));
				customer.setCustomerName(resultSet.getString("customer_name"));
				customer.setGender(resultSet.getString("gender"));
				customer.setPhoneNo(resultSet.getString("phone_no"));
				customer.setEmailId(resultSet.getString("email_id"));
				customers.add(customer);
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
		return customers;
	}

	@Override
	public Customer fetchById(Integer id) throws DataAccessException {
		if (id > 0) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			ResultSet resultSet = null;
			Customer customer = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "select customer_id,customer_name,gender,phone_no,email_id from customers where customer_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);
				prepstatement.setInt(1, id);

				resultSet = prepstatement.executeQuery();
				while (resultSet.next()) {				
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
		} else {
			throw new DataAccessException("negative value not allowed");
		}
	}

	@Override
	public Boolean insert(Customer c) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		String query = null;
		Integer result = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "insert into Customers(customer_id,customer_name,gender,phone_no,email_id) values(?,?,?,?,?)";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			
			prepstatement.setInt(1, c.getCustomerId());
			prepstatement.setString(2, c.getCustomerName());
			prepstatement.setString(3, c.getGender());
			prepstatement.setString(4, c.getPhoneNo());
			prepstatement.setString(5, c.getEmailId());
			
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
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean delete(Integer id) throws DataAccessException {
		if(id >0) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			Integer result = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "delete from customers where customer_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);
				
				prepstatement.setInt(1, id);
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
					DataAccessUtility.closeConnection(connection);
				} catch (SQLException e) {
					DataAccessException dataEx = new DataAccessException(e.getMessage(), e);
					throw dataEx;
				}
			}
			if(result > 0) {
				return true;
			}else {
				return false;
			}
		} else {
			throw new DataAccessException("negative value not allowed");
		}
		
	}

	@Override
	public Boolean update(Integer id, Customer c) throws DataAccessException {
		if(id > 0) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			Integer result = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "update customers set customer_id=?, customer_name=?, gender=?, phone_no=?, email_id=? where customer_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);
				
				prepstatement.setInt(1, c.getCustomerId());
				prepstatement.setString(2, c.getCustomerName());
				prepstatement.setString(3, c.getGender());
				prepstatement.setString(4, c.getPhoneNo());
				prepstatement.setString(5, c.getEmailId());
				prepstatement.setInt(6, id);
				
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
					DataAccessUtility.closeConnection(connection);
				} catch (SQLException e) {
					DataAccessException dataEx = new DataAccessException(e.getMessage(), e);
					throw dataEx;
				}
			}
			if(result > 0) {
				return true;
			}else {
				return false;
			}
		} else {
			throw new DataAccessException("negative value not allowed");
		}
	}

}

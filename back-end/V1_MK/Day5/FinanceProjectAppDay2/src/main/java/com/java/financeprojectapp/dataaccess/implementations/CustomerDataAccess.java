package com.java.financeprojectapp.dataaccess.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.java.financeprojectapp.dataaccess.abstractions.CustomerDataAccessContract;
import com.java.financeprojectapp.dataaccess.utility.DataAccessUtility;
import com.java.financeprojectapp.entities.Customer;
import com.java.financeprojectapp.entities.EmailRequest;
import com.java.financeprojectapp.exceptions.DataAccessException;
import com.java.financeprojectapp.servicelayer.entities.RandomPasswordGenerator;
import com.java.financeprojectapp.servicelayer.services.MailService;

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
				customer.setCustomerId(resultSet.getString("customer_id"));
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
	public Customer fetchById(String id) throws DataAccessException {
		if (id != null) {
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
				prepstatement.setString(1, id);

				resultSet = prepstatement.executeQuery();
				while (resultSet.next()) {
					customer = new Customer();
					customer.setCustomerId(resultSet.getString("customer_id"));
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
			throw new DataAccessException("null value not allowed");
		}
	}

	@Override
	public Boolean insert(Customer c) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		String query = null;
		Integer result = null;
		String CustomerId = "CUST" + UUID.randomUUID().toString().substring(0, 8);
		System.out.println(CustomerId);

		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "insert into Customers(customer_id,customer_name,gender,phone_no,email_id) values(?,?,?,?,?)";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);

			prepstatement.setString(1, CustomerId);
			prepstatement.setString(2, c.getCustomerName());
			prepstatement.setString(3, c.getGender());
			prepstatement.setString(4, c.getPhoneNo());
			prepstatement.setString(5, c.getEmailId());

			result = prepstatement.executeUpdate();

			if (result != null) {
				// set variables for email request body

				String loginpassword = RandomPasswordGenerator.generateRandomPassword(15);
				String tomail = c.getEmailId();
				String subject = "New Login Credentials";
				String body = "We're excited to inform you that your new login credentials have been generated and are ready for use. Please find below your login details:\r\n"
						+ "\r\n" + "Customer Id: " + CustomerId + "\r\n" +"Username: " + c.getEmailId() + "\r\n" + "Password: " + loginpassword + "\r\n"
						+ "\r\n"
						+ "For security purposes, we recommend that you change your password upon logging in for the first time. To do so, please follow the steps outlined on our website's password reset page.\r\n"
						+ "\r\n"
						+ "If you encounter any issues or have any questions regarding your new login credentials, please don't hesitate to reach out to our support team at manishssssskumaraaaaa@gmail.com. We're here to assist you every step of the way.\r\n"
						+ "\r\n"
						+ "Thank you for choosing Ganesh Finance Limited Company for your Loan needs. We look forward to serving you and ensuring a seamless experience.\r\n"
						+ "\r\n" + "Best regards,\r\n" + "\r\n" + "Ganesh Finance Limited Company\r\n"
						+ "manishssssskumaraaaaa@gmail.com\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "\r\n" + "";

				EmailRequest emailRequest = new EmailRequest(tomail, subject, body);

				UserCredentialsDataAccess ucdao = new UserCredentialsDataAccess();
				Boolean flag = ucdao.insert(emailRequest.getTo(), loginpassword, 0);
				if (flag) {
					MailService mailservice = new MailService();
					mailservice.sendEmail(emailRequest);
				}
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
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean delete(String id) throws DataAccessException {
		if (id != null) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			Integer result = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "delete from customers where customer_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);

				prepstatement.setString(1, id);
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
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new DataAccessException("null value not allowed");
		}

	}

	@Override
	public Boolean update(String id, Customer c) throws DataAccessException {
		if (id != null) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			Integer result = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "update customers set customer_id=?, customer_name=?, gender=?, phone_no=?, email_id=? where customer_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);

				// TODO Change the following line to not update the customer id.
				prepstatement.setString(1, c.getCustomerId());
				prepstatement.setString(2, c.getCustomerName());
				prepstatement.setString(3, c.getGender());
				prepstatement.setString(4, c.getPhoneNo());
				prepstatement.setString(5, c.getEmailId());
				prepstatement.setString(6, id);

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
			if (result > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new DataAccessException("null value not allowed");
		}
	}

	public Customer fetchByEmail(String email) throws DataAccessException {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			ResultSet resultSet = null;
			Customer customer = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "select customer_id,customer_name,gender,phone_no,email_id from customers where email_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);
				prepstatement.setString(1, email);

				resultSet = prepstatement.executeQuery();
				while (resultSet.next()) {
					customer = new Customer();
					customer.setCustomerId(resultSet.getString("customer_id"));
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
			throw new DataAccessException("Illegal Email Address");
		}
	}

}

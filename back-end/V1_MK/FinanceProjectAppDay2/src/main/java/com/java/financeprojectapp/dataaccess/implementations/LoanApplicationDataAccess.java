package com.java.financeprojectapp.dataaccess.implementations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.financeprojectapp.dataaccess.abstractions.LoanApplicationDataAccessContract;
import com.java.financeprojectapp.dataaccess.utility.DataAccessUtility;
import com.java.financeprojectapp.entities.LoanApplication;
import com.java.financeprojectapp.exceptions.DataAccessException;

public class LoanApplicationDataAccess implements LoanApplicationDataAccessContract {

	public LoanApplicationDataAccess() {
	}

	@Override
	public List<LoanApplication> fetchAll() throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		ResultSet resultSet = null;
		String query = null;
		List<LoanApplication> applications = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "select application_id, customer_id, loan_id, loan_amount, tenure, application_date, application_status, remarks, interest_rate from loan_applications";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			
			resultSet = prepstatement.executeQuery();
			applications = new ArrayList<LoanApplication>();			
			while (resultSet.next()) {
				LoanApplication application = new LoanApplication();
				application.setApplicationId(resultSet.getInt("application_id"));
				application.setCustomerId(resultSet.getInt("customer_id"));
				application.setLoanId(resultSet.getInt("loan_id"));
				application.setLoanAmount(resultSet.getFloat("loan_amount"));
				application.setTenure(resultSet.getInt("tenure"));
				application.setApplicationDate(resultSet.getDate("application_date"));
				application.setApplicationStatus(resultSet.getString("application_status"));
				application.setRemarks(resultSet.getString("remarks"));
				application.setInterestRate(resultSet.getFloat("interest_rate"));
				applications.add(application);
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
		return applications;
	}

	@Override
	public LoanApplication fetchById(Integer id) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		ResultSet resultSet = null;
		String query = null;
		LoanApplication application = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "select application_id, customer_id, loan_id, loan_amount, tenure, application_date, application_status, remarks, interest_rate from loan_applications where application_id=?";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			prepstatement.setInt(1, id);
			
			resultSet = prepstatement.executeQuery();			
			while (resultSet.next()) {
				application = new LoanApplication();
				application.setApplicationId(resultSet.getInt("application_id"));
				application.setCustomerId(resultSet.getInt("customer_id"));
				application.setLoanId(resultSet.getInt("loan_id"));
				application.setLoanAmount(resultSet.getFloat("loan_amount"));
				application.setTenure(resultSet.getInt("tenure"));
				application.setApplicationDate(resultSet.getDate("application_date"));
				application.setApplicationStatus(resultSet.getString("application_status"));
				application.setRemarks(resultSet.getString("remarks"));
				application.setInterestRate(resultSet.getFloat("interest_rate"));
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
		return application;
	}

	@Override
	public Boolean insert(LoanApplication la) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		String query = null;
		Integer result = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "insert into loan_applications (application_id, customer_id, loan_id, loan_amount, tenure, application_date, application_status, remarks, interest_rate) values(?,?,?,?,?,?,?,?,?)";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			
			prepstatement.setInt(1, la.getApplicationId());
			prepstatement.setInt(2, la.getCustomerId());
			prepstatement.setInt(3, la.getLoanId());
			prepstatement.setFloat(4, la.getLoanAmount());
			prepstatement.setInt(5, la.getTenure());
			prepstatement.setDate(6, la.getApplicationDate());
			prepstatement.setString(7, la.getApplicationStatus());
			prepstatement.setString(8, la.getRemarks());
			prepstatement.setFloat(9, la.getInterestRate());
			
			
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
				query = "delete from loan_applications where application_id=?";
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
	public Boolean update(Integer id, LoanApplication la) throws DataAccessException {
		if(id > 0) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			Integer result = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "update loan_applications set application_id=?, customer_id=?, loan_id=?, loan_amount=?, tenure=?, application_date=?, application_status=?, remarks=?, interest_rate=? where application_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);
				
				prepstatement.setInt(1, la.getApplicationId());
				prepstatement.setInt(2, la.getCustomerId());
				prepstatement.setInt(3, la.getLoanId());
				prepstatement.setFloat(4, la.getLoanAmount());
				prepstatement.setInt(5, la.getTenure());
				prepstatement.setDate(6, la.getApplicationDate());
				prepstatement.setString(7, la.getApplicationStatus());
				prepstatement.setString(8, la.getRemarks());
				prepstatement.setFloat(9, la.getInterestRate());
				prepstatement.setInt(10, id);
				
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

	public List<LoanApplication> fetchByDate(String date) throws DataAccessException {
		if(Date.valueOf(date) != null) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			ResultSet resultSet = null;
			String query = null;
			List<LoanApplication> applications = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "select application_id, customer_id, loan_id, loan_amount, tenure, application_date, application_status, remarks, interest_rate from loan_applications where application_date=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);
				prepstatement.setDate(1, Date.valueOf(date));
				
				resultSet = prepstatement.executeQuery();
				applications = new ArrayList<LoanApplication>();			
				while (resultSet.next()) {
					LoanApplication application = new LoanApplication();
					application.setApplicationId(resultSet.getInt("application_id"));
					application.setCustomerId(resultSet.getInt("customer_id"));
					application.setLoanId(resultSet.getInt("loan_id"));
					application.setLoanAmount(resultSet.getFloat("loan_amount"));
					application.setTenure(resultSet.getInt("tenure"));
					application.setApplicationDate(resultSet.getDate("application_date"));
					application.setApplicationStatus(resultSet.getString("application_status"));
					application.setRemarks(resultSet.getString("remarks"));
					application.setInterestRate(resultSet.getFloat("interest_rate"));
					applications.add(application);
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
			return applications;
		} else {
			throw new DataAccessException("Date Argument is Empty");
		}
	}

}

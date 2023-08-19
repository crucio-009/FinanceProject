package com.java.financeprojectapp.dataaccess.implementations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
			query = "select application_id, customer_id, loan_id, loan_amount, tenure, application_date, application_status, remarks, interest_rate, document_one, document_two from loan_applications";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);

			resultSet = prepstatement.executeQuery();
			applications = new ArrayList<LoanApplication>();
			while (resultSet.next()) {
				LoanApplication application = new LoanApplication();
				StringBuffer bufone = new StringBuffer();
				StringBuffer buftwo = new StringBuffer();
				String temp = null;
				BufferedReader readerone = new BufferedReader(
						new InputStreamReader(resultSet.getBlob("document_one").getBinaryStream()));
				BufferedReader readertwo = new BufferedReader(
						new InputStreamReader(resultSet.getBlob("document_two").getBinaryStream()));
				while ((temp = readerone.readLine()) != null) {
					bufone.append(temp);
				}
				while ((temp = readertwo.readLine()) != null) {
					buftwo.append(temp);
				}
				application.setApplicationId(resultSet.getString("application_id"));
				application.setCustomerId(resultSet.getString("customer_id"));
				application.setLoanId(resultSet.getInt("loan_id"));
				application.setLoanAmount(resultSet.getFloat("loan_amount"));
				application.setTenure(resultSet.getInt("tenure"));
				application.setApplicationDate(resultSet.getDate("application_date"));
				application.setApplicationStatus(resultSet.getString("application_status"));
				application.setRemarks(resultSet.getString("remarks"));
				application.setInterestRate(resultSet.getFloat("interest_rate"));
				application.setDocumentOne(bufone.toString());
				application.setDocumentTwo(buftwo.toString());
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
	public LoanApplication fetchById(String id) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		ResultSet resultSet = null;
		String query = null;
		LoanApplication application = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "select application_id, customer_id, loan_id, loan_amount, tenure, application_date, application_status, remarks, interest_rate, document_one, document_two from loan_applications where application_id=?";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			prepstatement.setString(1, id);

			resultSet = prepstatement.executeQuery();
			while (resultSet.next()) {
				application = new LoanApplication();
				StringBuffer bufone = new StringBuffer();
				StringBuffer buftwo = new StringBuffer();
				String temp = null;
				BufferedReader readerone = new BufferedReader(
						new InputStreamReader(resultSet.getBlob("document_one").getBinaryStream()));
				BufferedReader readertwo = new BufferedReader(
						new InputStreamReader(resultSet.getBlob("document_two").getBinaryStream()));
				while ((temp = readerone.readLine()) != null) {
					bufone.append(temp);
				}
				while ((temp = readertwo.readLine()) != null) {
					buftwo.append(temp);
				}

				application.setApplicationId(resultSet.getString("application_id"));
				application.setCustomerId(resultSet.getString("customer_id"));
				application.setLoanId(resultSet.getInt("loan_id"));
				application.setLoanAmount(resultSet.getFloat("loan_amount"));
				application.setTenure(resultSet.getInt("tenure"));
				application.setApplicationDate(resultSet.getDate("application_date"));
				application.setApplicationStatus(resultSet.getString("application_status"));
				application.setRemarks(resultSet.getString("remarks"));
				application.setInterestRate(resultSet.getFloat("interest_rate"));
				application.setDocumentOne(bufone.toString());
				application.setDocumentTwo(buftwo.toString());
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
		String LoanApplicationId = "LOAN" + UUID.randomUUID().toString().substring(0, 11);
		Date date = new Date(System.currentTimeMillis());

		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "insert into loan_applications (application_id, customer_id, loan_id, loan_amount, tenure, application_date, application_status, remarks, interest_rate, document_one, document_two) values(?,?,?,?,?,?,?,?,?,?,?)";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);

			Blob blobone = connection.createBlob();
			Blob blobtwo = connection.createBlob();
			blobone.setBytes(1, la.getDocumentOne().getBytes());
			blobtwo.setBytes(1, la.getDocumentTwo().getBytes());

			prepstatement.setString(1, LoanApplicationId);
			prepstatement.setString(2, la.getCustomerId());
			prepstatement.setInt(3, la.getLoanId());
			prepstatement.setFloat(4, la.getLoanAmount());
			prepstatement.setInt(5, la.getTenure());
			prepstatement.setDate(6, date);
			prepstatement.setString(7, la.getApplicationStatus().toUpperCase());
			prepstatement.setString(8, la.getRemarks());
			prepstatement.setFloat(9, la.getInterestRate());
			prepstatement.setBlob(10, blobone);
			prepstatement.setBlob(11, blobtwo);

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
	public Boolean delete(String id) throws DataAccessException {
		if (id != null) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			Integer result = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "delete from loan_applications where application_id=?";
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
	public Boolean update(String id, LoanApplication la) throws DataAccessException {
		if (id != null) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			Integer result = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "update loan_applications set application_id=?, customer_id=?, loan_id=?, loan_amount=?, tenure=?, application_date=?, application_status=?, remarks=?, interest_rate=?, document_one=?, document_two=? where application_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);

				Blob blobone = connection.createBlob();
				Blob blobtwo = connection.createBlob();
				blobone.setBytes(1, la.getDocumentOne().getBytes());
				blobtwo.setBytes(1, la.getDocumentTwo().getBytes());

				// TODO Change the following line to not update the application id.
				prepstatement.setString(1, la.getApplicationId());
				prepstatement.setString(2, la.getCustomerId());
				prepstatement.setInt(3, la.getLoanId());
				prepstatement.setFloat(4, la.getLoanAmount());
				prepstatement.setInt(5, la.getTenure());
				prepstatement.setDate(6, la.getApplicationDate());
				prepstatement.setString(7, la.getApplicationStatus().toUpperCase());
				prepstatement.setString(8, la.getRemarks());
				prepstatement.setFloat(9, la.getInterestRate());
				prepstatement.setBlob(10, blobone);
				prepstatement.setBlob(11, blobtwo);
				prepstatement.setString(12, id);

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
	public List<LoanApplication> fetchByDate(String date) throws DataAccessException {
		if (Date.valueOf(date) != null) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			ResultSet resultSet = null;
			String query = null;
			List<LoanApplication> applications = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "select * from loan_applications where application_date=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);
				prepstatement.setDate(1, Date.valueOf(date));

				resultSet = prepstatement.executeQuery();
				applications = new ArrayList<LoanApplication>();
				while (resultSet.next()) {
					LoanApplication application = new LoanApplication();
					StringBuffer bufone = new StringBuffer();
					StringBuffer buftwo = new StringBuffer();
					String temp = null;
					BufferedReader readerone = new BufferedReader(
							new InputStreamReader(resultSet.getBlob("document_one").getBinaryStream()));
					BufferedReader readertwo = new BufferedReader(
							new InputStreamReader(resultSet.getBlob("document_two").getBinaryStream()));
					while ((temp = readerone.readLine()) != null) {
						bufone.append(temp);
					}
					while ((temp = readertwo.readLine()) != null) {
						buftwo.append(temp);
					}
					application.setApplicationId(resultSet.getString("application_id"));
					application.setCustomerId(resultSet.getString("customer_id"));
					application.setLoanId(resultSet.getInt("loan_id"));
					application.setLoanAmount(resultSet.getFloat("loan_amount"));
					application.setTenure(resultSet.getInt("tenure"));
					application.setApplicationDate(resultSet.getDate("application_date"));
					application.setApplicationStatus(resultSet.getString("application_status"));
					application.setRemarks(resultSet.getString("remarks"));
					application.setInterestRate(resultSet.getFloat("interest_rate"));
					application.setDocumentOne(bufone.toString());
					application.setDocumentTwo(buftwo.toString());
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

	@Override
	public Boolean updateStatus(String id, String status) throws DataAccessException {
		if (status != null) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			Integer result = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "update loan_applications set application_status=? where application_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);

				prepstatement.setString(1, status.toUpperCase());
				prepstatement.setString(2, id);

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
	public List<LoanApplication> fetchAllPending() throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		ResultSet resultSet = null;
		String query = null;
		List<LoanApplication> applications = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "select application_id, customer_id, loan_id, loan_amount, tenure, application_date, application_status, remarks, interest_rate, document_one, document_two from loan_applications where application_status='PENDING' OR application_status='pending' OR application_status='Pending'";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);

			resultSet = prepstatement.executeQuery();
			applications = new ArrayList<LoanApplication>();
			while (resultSet.next()) {
				LoanApplication application = new LoanApplication();
				StringBuffer bufone = new StringBuffer();
				StringBuffer buftwo = new StringBuffer();
				String temp = null;
				BufferedReader readerone = new BufferedReader(
						new InputStreamReader(resultSet.getBlob("document_one").getBinaryStream()));
				BufferedReader readertwo = new BufferedReader(
						new InputStreamReader(resultSet.getBlob("document_two").getBinaryStream()));
				while ((temp = readerone.readLine()) != null) {
					bufone.append(temp);
				}
				while ((temp = readertwo.readLine()) != null) {
					buftwo.append(temp);
				}
				application.setApplicationId(resultSet.getString("application_id"));
				application.setCustomerId(resultSet.getString("customer_id"));
				application.setLoanId(resultSet.getInt("loan_id"));
				application.setLoanAmount(resultSet.getFloat("loan_amount"));
				application.setTenure(resultSet.getInt("tenure"));
				application.setApplicationDate(resultSet.getDate("application_date"));
				application.setApplicationStatus(resultSet.getString("application_status"));
				application.setRemarks(resultSet.getString("remarks"));
				application.setInterestRate(resultSet.getFloat("interest_rate"));
				application.setDocumentOne(bufone.toString());
				application.setDocumentTwo(buftwo.toString());
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
	public List<LoanApplication> fetchByType(String approvalStatus) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		ResultSet resultSet = null;
		String query = null;
		String status = approvalStatus.toUpperCase();
		List<LoanApplication> applications = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "select * from loan_applications where application_status=?";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			prepstatement.setString(1, status);

			resultSet = prepstatement.executeQuery();
			applications = new ArrayList<LoanApplication>();
			while (resultSet.next()) {
				LoanApplication application = new LoanApplication();
				StringBuffer bufone = new StringBuffer();
				StringBuffer buftwo = new StringBuffer();
				String temp = null;
				BufferedReader readerone = new BufferedReader(
						new InputStreamReader(resultSet.getBlob("document_one").getBinaryStream()));
				BufferedReader readertwo = new BufferedReader(
						new InputStreamReader(resultSet.getBlob("document_two").getBinaryStream()));
				while ((temp = readerone.readLine()) != null) {
					bufone.append(temp);
				}
				while ((temp = readertwo.readLine()) != null) {
					buftwo.append(temp);
				}
				application.setApplicationId(resultSet.getString("application_id"));
				application.setCustomerId(resultSet.getString("customer_id"));
				application.setLoanId(resultSet.getInt("loan_id"));
				application.setLoanAmount(resultSet.getFloat("loan_amount"));
				application.setTenure(resultSet.getInt("tenure"));
				application.setApplicationDate(resultSet.getDate("application_date"));
				application.setApplicationStatus(resultSet.getString("application_status"));
				application.setRemarks(resultSet.getString("remarks"));
				application.setInterestRate(resultSet.getFloat("interest_rate"));
				application.setDocumentOne(bufone.toString());
				application.setDocumentTwo(buftwo.toString());
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
	public Boolean updateAmount(String id, Float amount) throws DataAccessException {
		if (amount != null) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			Integer result = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "update loan_applications set loan_amount=loan_amount+? where application_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);

				prepstatement.setFloat(1, amount);
				prepstatement.setString(2, id);

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

}

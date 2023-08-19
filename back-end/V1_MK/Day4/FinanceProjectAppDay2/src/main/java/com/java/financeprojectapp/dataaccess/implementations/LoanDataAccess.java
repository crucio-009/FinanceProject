package com.java.financeprojectapp.dataaccess.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.financeprojectapp.dataaccess.abstractions.LoanDataAccessContract;
import com.java.financeprojectapp.dataaccess.utility.DataAccessUtility;
import com.java.financeprojectapp.entities.Loan;
import com.java.financeprojectapp.exceptions.DataAccessException;

public class LoanDataAccess implements LoanDataAccessContract {

	public LoanDataAccess() {
	}

	@Override
	public List<Loan> fetchAll() throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		ResultSet resultSet = null;
		String query = null;
		List<Loan> loans = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "select loan_id, loan_type, interest_rate, description from loans";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			
			resultSet = prepstatement.executeQuery();
			loans = new ArrayList<Loan>();			
			while (resultSet.next()) {
				Loan loan = new Loan();
				loan.setLoanId(resultSet.getInt("loan_id"));
				loan.setLoanType(resultSet.getString("loan_type"));
				loan.setInterest_rate(resultSet.getFloat("interest_rate"));
				loan.setDescription(resultSet.getString("description"));
				loans.add(loan);
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
		return loans;
	}

	@Override
	public Loan fetchById(Integer id) throws DataAccessException {
		if (id > 0) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			ResultSet resultSet = null;
			Loan loan = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "select loan_id, loan_type, interest_rate, description from loans where loan_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);
				prepstatement.setInt(1, id);

				resultSet = prepstatement.executeQuery();
				while (resultSet.next()) {				
					loan = new Loan();
					loan.setLoanId(resultSet.getInt("loan_id"));
					loan.setLoanType(resultSet.getString("loan_type"));
					loan.setInterest_rate(resultSet.getFloat("interest_rate"));
					loan.setDescription(resultSet.getString("description"));
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
			return loan;
		} else {
			throw new DataAccessException("negative value not allowed");
		}
	}

	@Override
	public Boolean insert(Loan l) throws DataAccessException {
		Connection connection = null;
		PreparedStatement prepstatement = null;
		String query = null;
		Integer result = null;
		try {
			DataAccessUtility.regsiterDriver();
			connection = DataAccessUtility.createConnection();
			query = "insert into Loans (loan_id, loan_type, interest_rate, description) values(?,?,?,?)";
			prepstatement = DataAccessUtility.prepareStatement(connection, query);
			
			prepstatement.setInt(1, l.getLoanId());
			prepstatement.setString(2, l.getLoanType());
			prepstatement.setFloat(3, l.getInterest_rate());
			prepstatement.setString(4, l.getDescription());
			
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
				query = "delete from loans where loan_id=?";
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
	public Boolean update(Integer id, Loan l) throws DataAccessException {
		if(id > 0) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			Integer result = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "update loans set loan_id=?, loan_type=?, interest_rate=?, description=? where loan_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);
				
				prepstatement.setInt(1, l.getLoanId());
				prepstatement.setString(2, l.getLoanType());
				prepstatement.setFloat(3, l.getInterest_rate());
				prepstatement.setString(4, l.getDescription());
				prepstatement.setInt(5, id);
				
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

	public Float fetchrate(int id) throws DataAccessException {
		if (id > 0) {
			Connection connection = null;
			PreparedStatement prepstatement = null;
			String query = null;
			ResultSet resultSet = null;
			Float rate = null;
			try {
				DataAccessUtility.regsiterDriver();
				connection = DataAccessUtility.createConnection();
				query = "select interest_rate from loans where loan_id=?";
				prepstatement = DataAccessUtility.prepareStatement(connection, query);
				prepstatement.setInt(1, id);

				resultSet = prepstatement.executeQuery();
				while (resultSet.next()) {
					rate = resultSet.getFloat("interest_rate");
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
			return rate;
		} else {
			throw new DataAccessException("negative value not allowed");
		}
	}

}

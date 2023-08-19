package com.java.financeprojectapp.dataaccess.abstractions;

import java.util.List;

import com.java.financeprojectapp.dataaccess.contracts.DataAccessContract;
import com.java.financeprojectapp.entities.LoanApplication;
import com.java.financeprojectapp.exceptions.DataAccessException;

public interface LoanApplicationDataAccessContract extends DataAccessContract<LoanApplication, String> {

	Boolean updateStatus(String id, String status) throws DataAccessException;
	
	Boolean updateAmount(String id, Float amount) throws DataAccessException;

	List<LoanApplication> fetchAllPending() throws DataAccessException;

	List<LoanApplication> fetchByDate(String date) throws DataAccessException;

	List<LoanApplication> fetchByType(String type) throws DataAccessException;
}

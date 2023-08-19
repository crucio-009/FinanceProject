package com.java.financeprojectapp.dataaccess.abstractions;

import java.util.List;

import com.java.financeprojectapp.dataaccess.contracts.DataAccessContract;
import com.java.financeprojectapp.entities.LoanApplication;
import com.java.financeprojectapp.exceptions.DataAccessException;

public interface LoanApplicationDataAccessContract extends DataAccessContract<LoanApplication, String> {

	Boolean updateStatus(String id, String status) throws DataAccessException;

	List<LoanApplication> fetchAllPending() throws DataAccessException;
}

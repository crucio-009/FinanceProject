package com.java.financeprojectapp.businesslayer.abstractions;

import java.util.List;

import com.java.financeprojectapp.businesslayer.contracts.BusinessComponentContract;
import com.java.financeprojectapp.entities.LoanApplication;
import com.java.financeprojectapp.exceptions.BusinessComponetException;

public interface LoanApplicationBusinessComponentContract extends BusinessComponentContract<LoanApplication, String> {

	List<LoanApplication> getLoanApplicationsByDate(String date) throws BusinessComponetException;

	List<LoanApplication> getPendingApplications() throws BusinessComponetException;

	Boolean modifyStatus(String id, String status) throws BusinessComponetException;
}

package com.java.financeprojectapp.businesslayer.abstractions;

import com.java.financeprojectapp.businesslayer.contracts.BusinessComponentContract;
import com.java.financeprojectapp.entities.Loan;
import com.java.financeprojectapp.exceptions.BusinessComponetException;

public interface LoanBusinessComponentContract extends BusinessComponentContract<Loan, Integer> {
	
	float getInterestRate(int id) throws BusinessComponetException;
}

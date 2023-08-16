package com.java.financeprojectapp.businesslayer.implementations;

import java.util.List;

import com.java.financeprojectapp.businesslayer.abstractions.LoanApplicationBusinessComponentContract;
import com.java.financeprojectapp.entities.LoanApplication;
import com.java.financeprojectapp.exceptions.BusinessComponetException;

public class LoanApplicationBusinessComponent implements LoanApplicationBusinessComponentContract {

	public LoanApplicationBusinessComponent() {
	}

	@Override
	public List<LoanApplication> getAll() throws BusinessComponetException {
		return null;
	}

	@Override
	public LoanApplication getById(Integer id) throws BusinessComponetException {
		return null;
	}

	@Override
	public Boolean add(LoanApplication data) throws BusinessComponetException {
		return null;
	}

	@Override
	public Boolean remove(Integer id) throws BusinessComponetException {
		return null;
	}

	@Override
	public Boolean modify(Integer id, LoanApplication data) throws BusinessComponetException {
		return null;
	}

}

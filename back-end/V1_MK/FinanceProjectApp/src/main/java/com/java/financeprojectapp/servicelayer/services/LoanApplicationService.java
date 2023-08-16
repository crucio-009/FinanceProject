package com.java.financeprojectapp.servicelayer.services;

import com.java.financeprojectapp.businesslayer.implementations.LoanApplicationBusinessComponent;

public class LoanApplicationService {

	public LoanApplicationBusinessComponent labo = new LoanApplicationBusinessComponent();

	public LoanApplicationService() {
		super();
	}

	public LoanApplicationService(LoanApplicationBusinessComponent labo) {
		super();
		this.labo = labo;
	}
	
	

}

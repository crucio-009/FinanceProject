package com.java.financeprojectapp.entities;

import java.sql.Date;

public class LoanApplication {

	private int applicationId;
	private int customerId;
	private int loanId;
	private float loanAmount;
	private float interestRate;
	private int tenure;
	private Date applicationDate;
	private String applicationStatus;
	private String remarks;
	
	public LoanApplication() {
		super();
	}

	public LoanApplication(int applicationId, int customerId, int loanId, float loanAmount, float interestRate,
			int tenure, Date applicationDate, String applicationStatus, String remarks) {
		super();
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.loanId = loanId;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.tenure = tenure;
		this.applicationDate = applicationDate;
		this.applicationStatus = applicationStatus;
		this.remarks = remarks;
	}

	public LoanApplication(int applicationId, int customerId, int loanId, float loanAmount, float interestRate,
			int tenure, String applicationDate, String applicationStatus, String remarks) {
		super();
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.loanId = loanId;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.tenure = tenure;
		this.applicationDate = Date.valueOf(applicationDate);
		this.applicationStatus = applicationStatus;
		this.remarks = remarks;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public float getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(float loanAmount) {
		this.loanAmount = loanAmount;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = Date.valueOf(applicationDate);
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if (!(obj instanceof Loan))
			return false;

		LoanApplication other = (LoanApplication) obj;
		if (this.applicationId != other.applicationId)
			return false;

		return true;
	}

	public void setApplicationDate(Date date) {
		this.applicationDate = date;
	}
	
}

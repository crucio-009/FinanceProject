package com.java.financeprojectapp.entities;

public class Loan {

	private int loanId;
	private String loanType;
	private String description;
	
	public Loan() {
	}

	public Loan(int loanId, String loanType, String description) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.description = description;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;

		if (this == arg0)
			return true;

		if (!(arg0 instanceof Loan))
			return false;

		Loan other = (Loan) arg0;
		if (this.loanId != other.loanId)
			return false;

		return true;
	}
	
}

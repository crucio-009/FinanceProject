package com.java.financeprojectapp.entities;

public class Customer {

	private String customerId;
	private String customerName;
	private String gender;
	private String phoneNo;
	private String emailId;

	public Customer() {
		super();
	}

	public Customer(String customerId, String customerName, String gender, String phoneNo, String emailId) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;

		if (this == arg0)
			return true;

		if (!(arg0 instanceof Customer))
			return false;

		Customer other = (Customer) arg0;
		if (!this.customerId.equals(other.customerId)) {
			System.out.println("hello");
			return false;
		}

		return true;
	}

}

package com.java.financeprojectapp.businesslayer.abstractions;

import com.java.financeprojectapp.businesslayer.contracts.*;
import com.java.financeprojectapp.entities.Customer;
import com.java.financeprojectapp.exceptions.BusinessComponetException;

public interface CustomerBusinessComponentContract extends BusinessComponentContract<Customer, Integer> {
	
	Customer searchCustomerByEmail(String email) throws BusinessComponetException;
;}

package com.java.financeprojectapp.businesslayer.abstractions;

import com.java.financeprojectapp.businesslayer.contracts.BusinessComponentContract;
import com.java.financeprojectapp.entities.Customer;
import com.java.financeprojectapp.exceptions.BusinessComponetException;

public interface CustomerBusinessComponentContract extends BusinessComponentContract<Customer, String> {

	Customer searchCustomerByEmail(String email) throws BusinessComponetException;;
}

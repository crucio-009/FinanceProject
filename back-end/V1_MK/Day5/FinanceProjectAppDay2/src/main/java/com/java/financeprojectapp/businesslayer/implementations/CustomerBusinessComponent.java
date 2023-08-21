package com.java.financeprojectapp.businesslayer.implementations;

import java.util.List;

import com.java.financeprojectapp.businesslayer.abstractions.CustomerBusinessComponentContract;
import com.java.financeprojectapp.dataaccess.implementations.CustomerDataAccess;
import com.java.financeprojectapp.entities.Customer;
import com.java.financeprojectapp.exceptions.BusinessComponetException;
import com.java.financeprojectapp.exceptions.DataAccessException;

public class CustomerBusinessComponent implements CustomerBusinessComponentContract {

	private CustomerDataAccess cdao = new CustomerDataAccess();

	public CustomerBusinessComponent() {
		super();
	}

	public CustomerBusinessComponent(CustomerDataAccess cdao) {
		super();
		this.cdao = cdao;
	}

	@Override
	public List<Customer> getAll() throws BusinessComponetException {
		try {
			List<Customer> list = cdao.fetchAll();
			if (list == null) {
				throw new BusinessComponetException("Something went wrong...Try later");
			} else if (list.size() == 0) {
				throw new BusinessComponetException("No Customer records found");
			} else {
				return list;
			}
		} catch (DataAccessException ex) {
			throw new BusinessComponetException(ex.getMessage(), ex);
		} catch (BusinessComponetException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BusinessComponetException(e.getMessage(), e);
		}
	}

	@Override
	public Customer getById(String id) throws BusinessComponetException {
		try {
			Customer c = cdao.fetchById(id);
			if (c == null)
				throw new BusinessComponetException("No such Customer found");
			else
				return c;
		} catch (DataAccessException ex) {
			throw new BusinessComponetException(ex.getMessage(), ex);
		} catch (BusinessComponetException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BusinessComponetException(e.getMessage(), e);
		}
	}

	@Override
	public Boolean add(Customer c) throws BusinessComponetException {
		try {
			Boolean flag = cdao.insert(c);
			if (flag == null) {
				throw new BusinessComponetException("Customer details could not be added.");
			} else {
				return flag;
			}
		} catch (DataAccessException ex) {
			throw new BusinessComponetException(ex.getMessage(), ex);
		} catch (BusinessComponetException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BusinessComponetException(e.getMessage(), e);
		}
	}

	@Override
	public Boolean remove(String id) throws BusinessComponetException {
		try {
			Boolean flag = cdao.delete(id);
			if (flag == null) {
				throw new BusinessComponetException("Customer details could not be deleted");
			} else {
				return flag;
			}
		} catch (DataAccessException ex) {
			throw new BusinessComponetException(ex.getMessage(), ex);
		} catch (BusinessComponetException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BusinessComponetException(e.getMessage(), e);
		}
	}

	@Override
	public Boolean modify(String id, Customer c) throws BusinessComponetException {
		try {
			Boolean flag = cdao.update(id, c);
			if (flag == null) {
				throw new BusinessComponetException("Customer could not be modified");
			} else {
				return flag;
			}
		} catch (DataAccessException ex) {
			throw new BusinessComponetException(ex.getMessage(), ex);
		} catch (BusinessComponetException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BusinessComponetException(e.getMessage(), e);
		}
	}

	@Override
	public Customer searchCustomerByEmail(String email) throws BusinessComponetException {
		try {
			Customer c = cdao.fetchByEmail(email);
			if (c == null)
				throw new BusinessComponetException("No such Customer found");
			else
				return c;
		} catch (DataAccessException ex) {
			throw new BusinessComponetException(ex.getMessage(), ex);
		} catch (BusinessComponetException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BusinessComponetException(e.getMessage(), e);
		}
	}

}

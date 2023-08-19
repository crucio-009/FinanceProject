package com.java.financeprojectapp.businesslayer.implementations;

import java.util.List;

import com.java.financeprojectapp.businesslayer.abstractions.LoanBusinessComponentContract;
import com.java.financeprojectapp.dataaccess.implementations.LoanDataAccess;
import com.java.financeprojectapp.entities.Loan;
import com.java.financeprojectapp.exceptions.BusinessComponetException;
import com.java.financeprojectapp.exceptions.DataAccessException;

public class LoanBusinessComponent implements LoanBusinessComponentContract {
	
	private LoanDataAccess ldao = new LoanDataAccess();

	public LoanBusinessComponent() {
		super();
	}

	public LoanBusinessComponent(LoanDataAccess ldao) {
		super();
		this.ldao = ldao;
	}

	@Override
	public List<Loan> getAll() throws BusinessComponetException {
		try {
			List<Loan> list = ldao.fetchAll();
			if (list == null) {
				throw new BusinessComponetException("Something went wrong...Try later");
			} else if (list.size() == 0) {
				throw new BusinessComponetException("No Loan records found");
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
	public Loan getById(Integer id) throws BusinessComponetException {
		try {
			Loan l = ldao.fetchById(id);
			if (l == null)
				throw new BusinessComponetException("No such Loan found");
			else
				return l;
		} catch (DataAccessException ex) {
			throw new BusinessComponetException(ex.getMessage(), ex);
		} catch (BusinessComponetException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BusinessComponetException(e.getMessage(), e);
		}
	}

	@Override
	public Boolean add(Loan data) throws BusinessComponetException {
		try {
			Boolean flag = ldao.insert(data);
			if(flag == null) {
				throw new BusinessComponetException("Loan details could not be added.");
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
	public Boolean remove(Integer id) throws BusinessComponetException {
		try {
			Boolean flag = ldao.delete(id);
			if(flag == null) {
				throw new BusinessComponetException("Loan details could not be deleted");
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
	public Boolean modify(Integer id, Loan data) throws BusinessComponetException {
		try {
			Boolean flag = ldao.update(id, data);
			if(flag == null) {
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

	public float getInterestRate(int id) throws BusinessComponetException {
		try {
			Float rate = ldao.fetchrate(id);
			if(rate == null) {
				throw new BusinessComponetException("Customer could not be modified");
			} else {
				return rate;
			}
		} catch (DataAccessException ex) {
			throw new BusinessComponetException(ex.getMessage(), ex);
		} catch (BusinessComponetException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BusinessComponetException(e.getMessage(), e);
		}
	}

}

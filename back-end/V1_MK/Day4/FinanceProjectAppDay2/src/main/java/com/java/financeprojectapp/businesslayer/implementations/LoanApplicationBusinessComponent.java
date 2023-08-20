package com.java.financeprojectapp.businesslayer.implementations;

import java.util.List;

import com.java.financeprojectapp.businesslayer.abstractions.LoanApplicationBusinessComponentContract;
import com.java.financeprojectapp.dataaccess.implementations.LoanApplicationDataAccess;
import com.java.financeprojectapp.entities.LoanApplication;
import com.java.financeprojectapp.exceptions.BusinessComponetException;
import com.java.financeprojectapp.exceptions.DataAccessException;

public class LoanApplicationBusinessComponent implements LoanApplicationBusinessComponentContract {

	private LoanApplicationDataAccess lapdao = new LoanApplicationDataAccess();

	public LoanApplicationBusinessComponent() {
		super();
	}

	public LoanApplicationBusinessComponent(LoanApplicationDataAccess lapdao) {
		super();
		this.lapdao = lapdao;
	}

	@Override
	public List<LoanApplication> getAll() throws BusinessComponetException {
		try {
			List<LoanApplication> list = lapdao.fetchAll();
			if (list == null) {
				throw new BusinessComponetException("Something went wrong...Try later");
			} else if (list.size() == 0) {
				throw new BusinessComponetException("No Loan Application records found");
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
	public LoanApplication getById(String id) throws BusinessComponetException {
		try {
			LoanApplication la = lapdao.fetchById(id);
			if (la == null)
				throw new BusinessComponetException("No such Loan Application found");
			else
				return la;
		} catch (DataAccessException ex) {
			throw new BusinessComponetException(ex.getMessage(), ex);
		} catch (BusinessComponetException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BusinessComponetException(e.getMessage(), e);
		}
	}

	@Override
	public Boolean add(LoanApplication data) throws BusinessComponetException {
		try {
			Boolean flag = lapdao.insert(data);
			if (flag == null) {
				throw new BusinessComponetException("Loan Application details could not be added.");
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
			Boolean flag = lapdao.delete(id);
			if (flag == null) {
				throw new BusinessComponetException("Loan Application details could not be deleted");
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
	public Boolean modify(String id, LoanApplication data) throws BusinessComponetException {
		try {
			Boolean flag = lapdao.update(id, data);
			if (flag == null) {
				throw new BusinessComponetException("Loan Application details could not be modified");
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
	public List<LoanApplication> getLoanApplicationsByDate(String date) throws BusinessComponetException {
		try {
			List<LoanApplication> list = lapdao.fetchByDate(date);
			if (list == null) {
				throw new BusinessComponetException("Something went wrong...Try later");
			} else if (list.size() == 0) {
				throw new BusinessComponetException("No Loan Application records found for the date");
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
	public Boolean modifyStatus(String id, String status) throws BusinessComponetException {
		try {
			Boolean flag = lapdao.updateStatus(id, status);
			if (flag == null) {
				throw new BusinessComponetException("Loan Application details could not be modified");
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
	public List<LoanApplication> getPendingApplications() throws BusinessComponetException {
		try {
			List<LoanApplication> list = lapdao.fetchAllPending();
			if (list == null) {
				throw new BusinessComponetException("Something went wrong...Try later");
			} else if (list.size() == 0) {
				throw new BusinessComponetException("No Loan Application records found");
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
	public List<LoanApplication> getLoanApplicationsByType(String type) throws BusinessComponetException {
		try {
			List<LoanApplication> list = lapdao.fetchByType(type);
			if (list == null) {
				throw new BusinessComponetException("Something went wrong...Try later");
			} else if (list.size() == 0) {
				System.out.println(list);
				throw new BusinessComponetException("No Loan Application records found for that type");
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
	public Boolean modifyAmount(String id, Float amount) throws BusinessComponetException {
		try {
			Boolean flag = lapdao.updateAmount(id, amount);
			if (flag == null) {
				throw new BusinessComponetException("Loan Application details could not be modified");
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
	public List<LoanApplication> getLoanApplicationsByCustIdType(String id, String type)
			throws BusinessComponetException {
		try {
			List<LoanApplication> list = lapdao.fetchByCustIdAndType(id, type);
			if (list == null) {
				throw new BusinessComponetException("Something went wrong...Try later");
			} else if (list.size() == 0) {
				System.out.println(list);
				throw new BusinessComponetException("No Loan Application records found for that type");
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

}

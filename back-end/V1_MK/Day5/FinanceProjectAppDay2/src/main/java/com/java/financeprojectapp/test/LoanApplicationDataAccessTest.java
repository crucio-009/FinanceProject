/**
 * 
 */
package com.java.financeprojectapp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.java.financeprojectapp.dataaccess.implementations.LoanApplicationDataAccess;
import com.java.financeprojectapp.entities.LoanApplication;
import com.java.financeprojectapp.exceptions.DataAccessException;

/**
 * 
 */
class LoanApplicationDataAccessTest {
	
	private LoanApplicationDataAccess lapdao = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		lapdao = new LoanApplicationDataAccess();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		lapdao = null;
	}

	/**
	 * Test method for {@link com.java.financeprojectapp.dataaccess.implementations.LoanApplicationDataAccess#fetchByDate(java.lang.String)}.
	 */
	@Test
	void testFetchByDate() {
		try {
			List<LoanApplication> list = lapdao.fetchByDate("2023-08-21");
			assertNotNull(list);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Test method for {@link com.java.financeprojectapp.dataaccess.implementations.LoanApplicationDataAccess#fetchAllPending()}.
	 */
	@Test
	void testFetchAllPending() {
		try {
			List<LoanApplication> list = lapdao.fetchAllPending();
			assertNotNull(list);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Test method for {@link com.java.financeprojectapp.dataaccess.implementations.LoanApplicationDataAccess#fetchByType(java.lang.String)}.
	 */
	@Test
	void testFetchByType() {
		try {
			List<LoanApplication> list = lapdao.fetchByType("Approved");
			assertNotNull(list);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Test method for {@link com.java.financeprojectapp.dataaccess.implementations.LoanApplicationDataAccess#updateAmount(java.lang.String, java.lang.Float)}.
	 */
	@Test
	void testUpdateAmount() {
		try {
			Boolean flag = lapdao.updateAmount("1000000001", (float) 20000);
			assertTrue(flag);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.java.financeprojectapp.dataaccess.implementations.LoanApplicationDataAccess#fetchByCustIdAndType(java.lang.String, java.lang.String)}.
	 */
	@Test
	void testFetchByCustIdAndType() {
		try {
			List<LoanApplication> list = lapdao.fetchByCustIdAndType("1000001", "Pending");
			assertNotNull(list);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Test method for {@link com.java.financeprojectapp.dataaccess.implementations.LoanApplicationDataAccess#fetchByLoanId(java.lang.String)}.
	 */
	@Test
	void testFetchByLoanId() {
		try {
			List<LoanApplication> list = lapdao.fetchByLoanId(1000);
			assertNotNull(list);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}	
	}

}

package com.java.financeprojectapp.test;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.java.financeprojectapp.dataaccess.implementations.LoanDataAccess;
import com.java.financeprojectapp.entities.Loan;
import com.java.financeprojectapp.exceptions.DataAccessException;

/**
 *
 */
class LoanDataAccessTest {

	private LoanDataAccess ldao = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		ldao = new LoanDataAccess();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		ldao = null;
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.LoanDataAccess#insert()}.
	 */
	@Test
	void testInsert() {
		try {
			Loan l = new Loan(1111, "Test Loan", (float) 9.99, "Test Loan for JUnit");
			Boolean flag = ldao.insert(l);
			assertNotNull(flag);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.LoanDataAccess#update(java.lang.int)}.
	 */
	@Test
	void testUpdate() {
		try {
			Loan l = new Loan(1000, "Test Loan", (float) 10.99, "Test Loan for JUnit");
			Boolean flag = ldao.update(1000, l);
			assertNotNull(flag);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.LoanDataAccess#delete(java.lang.int)}.
	 */
	@Test
	void testDelete() {
		try {
			Boolean flag = ldao.delete(1111);
			assertNotNull(flag);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}

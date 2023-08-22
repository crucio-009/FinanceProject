/**
 * 
 */
package com.java.financeprojectapp.test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.java.financeprojectapp.dataaccess.implementations.UserCredentialsDataAccess;
import com.java.financeprojectapp.exceptions.DataAccessException;

/**
 * 
 */
class UserCredentialsDataAccessTest {

	private UserCredentialsDataAccess ucdao = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		ucdao = new UserCredentialsDataAccess();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		ucdao = null;
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.UserCredentialsDataAccess#insert(java.lang.String, java.lang.String, int)}.
	 */
	@Test
	void testInsert() {
		try {
			Boolean flag = ucdao.insert("test@test.com", "testpassword", 0);
			assertTrue(flag);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.UserCredentialsDataAccess#update(java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	void testFailedUpdate() {
		try {
			Boolean flag = ucdao.update("test@test.com", "password", "newtestpassword", 0);
			assertFalse(flag);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}

/**
 * 
 */
package com.java.financeprojectapp.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.java.financeprojectapp.dataaccess.implementations.CustomerDataAccess;
import com.java.financeprojectapp.entities.Customer;
import com.java.financeprojectapp.exceptions.DataAccessException;

/**
 * 
 */
class CustomerDataAccessTest {

	private CustomerDataAccess cdao = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		cdao = new CustomerDataAccess();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		cdao = null;
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.CustomerDataAccess#fetchAll(java.lang.String)}.
	 */
	@Test
	void testFetchAll() {
		try {
			List<Customer> list = cdao.fetchAll();
			assertNotNull(list);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.CustomerDataAccess#fetchById(java.lang.String)}.
	 */
	@Test
	void testFetchById() {
		try {
			Customer actualCustomer = cdao.fetchById("CUST725b8d82");
			Customer expectedCustomer = new Customer("CUST725b8d82", "TestCustomer", "Other", "9999999999", "test@test.com");
			assertEquals(expectedCustomer, actualCustomer);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.CustomerDataAccess#fetchByEmail(java.lang.String)}.
	 */
	@Test
	void testFetchByEmail() {
		try {
			Customer c = cdao.fetchByEmail("test@test.com");
			assertNotNull(c);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}

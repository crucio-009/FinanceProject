/**
 * 
 */
package com.java.financeprojectapp.dataaccess.implementations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	 * {@link com.java.financeprojectapp.dataaccess.implementations.CustomerDataAccess#fetchById(java.lang.String)}.
	 */
	@Test
	void testFetchById() {
		Customer expectedCustomer = new Customer("1000001", "TestCustomer", "Other", "9999999999", "test@test.com");
		try {
			Customer actualCustomer = cdao.fetchById("1000001");
			assertEquals(expectedCustomer, actualCustomer);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.CustomerDataAccess#insert(com.java.financeprojectapp.entities.Customer)}.
	 */
	@Test
	void testInsert() {
		Customer expectedCustomer = new Customer();
		expectedCustomer.setCustomerName("TestCustomer");
		expectedCustomer.setEmailId("test@test.com");
		expectedCustomer.setGender("Other");
		expectedCustomer.setPhoneNo("9999999999");
		try {
			Boolean flag = cdao.insert(expectedCustomer);
			assertEquals(flag, true);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.CustomerDataAccess#delete(java.lang.String)}.
	 */
	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.CustomerDataAccess#update(java.lang.String, com.java.financeprojectapp.entities.Customer)}.
	 */
	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.java.financeprojectapp.dataaccess.implementations.CustomerDataAccess#fetchByEmail(java.lang.String)}.
	 */
	@Test
	void testFetchByEmail() {
		fail("Not yet implemented");
	}

}

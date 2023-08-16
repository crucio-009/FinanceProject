package com.java.financeapp.tests;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.java.financeapp.*;
import com.java.financeapp.entities.Customer;
import com.java.financeapp.exceptions.DataAccessException;


public class UnitTests {
	private static DataAccess dao;
	
	@BeforeClass
	public static void initialize() {
		dao = new DataAccess();
	}
	
	@AfterClass
	public static void cleanUp() {		
		dao = null;
	}
	
	@Test
	public void fetchByIdPositiveTest() {
		try {
			Customer actualProduct = dao.fetchById(1);
			Customer expectedProduct = new Customer(1, "likky", "M", "7416685123", "likky@gmail.com");
			Assert.assertEquals(expectedProduct, actualProduct);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	

}


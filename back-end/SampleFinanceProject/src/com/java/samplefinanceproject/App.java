package com.java.samplefinanceproject;

import com.java.samplefinanceproject.exceptions.DataAccessException;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataAccess dao = new DataAccess();
		try {
		dao.fetchById(1);
		}catch (DataAccessException e) {
			e.printStackTrace();
		}
		

	}

}

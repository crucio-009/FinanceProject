package com.java.financeprojectapp.dataaccess.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataAccessUtility {
	public static void regsiterDriver() throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}

	public static Connection createConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "System", "Oracle2023");
	}

	public static PreparedStatement prepareStatement(Connection connection, String query) throws SQLException {
		return connection.prepareStatement(query);
	}

	public static void closeConnection(Connection connection) throws SQLException {
		if (connection != null)
			connection.close();
	}
}

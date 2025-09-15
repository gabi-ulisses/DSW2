package edu.ifsp.loja.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionFactory {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3307/loja", "root", "root");

		return conn;
	}
}

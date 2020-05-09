package br.com.sintaxerror.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/dbclinica";
			String login = "root";
			String senha = "";
			
			return DriverManager.getConnection(url, login, senha);
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
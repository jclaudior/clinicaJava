package br.com.sintaxerror.util;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

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
	//Testar Conecxao
	/*public static void main(String[] args) {
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			
			JOptionPane.showMessageDialog(null, "Conectado");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
}
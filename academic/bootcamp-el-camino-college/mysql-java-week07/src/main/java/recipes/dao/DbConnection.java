package recipes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import recipes.exception.DbException;

public class DbConnection {
	private static final String SCHEMA = "recipes";
	private static final String USER = "recipes";
	private static final String PASSWORD = "recipes";
	private static final String HOST = "localhost";
	private static final int PORT = 3306;
	
	public static Connection getConnection() {
		//https://stackoverflow.com/questions/10882475/how-to-setup-jdbc-in-eclipse
		
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", 
				HOST, PORT, SCHEMA, USER, PASSWORD);
		System.out.println("Connecting with url=" + url);
		
		try {
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Successfully obtained a connection!");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error getting connection.");
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	

}

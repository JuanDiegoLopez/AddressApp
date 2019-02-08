package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {
	private Connection connection;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "testuser";
	private String password = "mint01";
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void startConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection () {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

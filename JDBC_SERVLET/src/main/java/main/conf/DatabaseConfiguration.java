package main.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfiguration {
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "admin");
		return connection;
	}
	
	public static void createTables() throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		stmt.execute("CREATE TABLE IF NOT EXISTS employee (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), email VARCHAR(100), mobile VARCHAR(15), department VARCHAR(50))");

	}
	public static void init() throws SQLException {
		createTables();
		System.out.println("Database Connected");
	}
	
}

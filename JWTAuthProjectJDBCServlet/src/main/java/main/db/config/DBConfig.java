package main.db.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConfig {
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "admin");
		return connection;
	}
	
	public static Connection createTables() throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS employee (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "email VARCHAR(100), " +
                "mobile VARCHAR(15), " +
                "department VARCHAR(50), " +
                "username VARCHAR(50), " +
                "password VARCHAR(100), " +
                "`role` VARCHAR(50) NOT NULL" + 
                ")";
		     System.out.println("Executing SQL: " + sql);
		     stmt.execute(sql);
		     System.out.println("Table created or already exists.");
		return conn;

	}
	public static Connection init() throws SQLException {
		Connection  conn = createTables();
		System.out.println("Database Connected");
		return conn;
	}
}

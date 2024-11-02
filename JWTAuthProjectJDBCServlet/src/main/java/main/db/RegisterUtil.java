package main.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.dto.EmployeeDto;

public class RegisterUtil {
	public static void insertEmployee(Connection connection, EmployeeDto employee) throws SQLException {
	    String sql = "INSERT INTO employee (name, email, mobile, department, username, password, `role`) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, employee.getName());
	        stmt.setString(2, employee.getEmail());
	        stmt.setString(3, employee.getMobile());
	        stmt.setString(4, employee.getDepartment());
	        stmt.setString(5, employee.getUsername());
	        stmt.setString(6, employee.getPassword());
	        stmt.setString(7, employee.getRole());

	        int rowsInserted = stmt.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("A new employee was inserted successfully!");
	        } else {
	            System.out.println("Employee insertion failed.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error inserting employee: " + e.getMessage());
	        throw e;
	    }
	}

}

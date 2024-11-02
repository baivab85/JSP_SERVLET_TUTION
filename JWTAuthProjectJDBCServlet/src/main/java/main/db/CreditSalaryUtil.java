package main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreditSalaryUtil {

    public static String insertEmployeeSalary(Connection conn,int employeeId, double salary) throws SQLException{
        String insertQuery = "INSERT INTO  employeesalary (employeeId, salary) VALUES (?, ?)";

       
        PreparedStatement stmt = conn.prepareStatement(insertQuery);

            // Set parameters
            stmt.setInt(1, employeeId);
            stmt.setDouble(2, salary);

            // Execute the update
            int rowsInserted = stmt.executeUpdate();
           
            if (rowsInserted > 0) {
                return "A new employee salary record was inserted successfully.";
            }
			return "error inserting data";
        
    }
    }

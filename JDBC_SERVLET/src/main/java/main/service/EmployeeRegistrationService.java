package main.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.conf.DatabaseConfiguration;
import main.dto.Employee;

public class EmployeeRegistrationService {
 public static Employee register(Employee e) throws SQLException {
	 Connection conn = DatabaseConfiguration.getConnection();
	 System.out.println("Connected Succesfully");
	 
	 
	 String sql = "INSERT INTO employee (name, email, mobile, department) VALUES (?, ?, ?, ?)";
	 PreparedStatement pre = conn.prepareStatement(sql);
	 pre.setString(1,e.getName());
	 pre.setString(2,e.getEmail());
	 pre.setString(3,e.getMobile());
	 pre.setString(4,e.getDepartment());
	 
	 int rowsInserted = pre.executeUpdate();
	 if (rowsInserted > 0) {
         System.out.println("Employee data inserted successfully!");
     } else {
         System.out.println("Failed to insert employee data.");
     }
	 pre.close();
	 conn.close();
	return e;
 }
 public static void getAllEmployees() throws SQLException {
	 Connection conn = DatabaseConfiguration.getConnection();
	 String query = "select * from employee";
	 
	 PreparedStatement pre = conn.prepareStatement(query);
	 ResultSet rs = pre.executeQuery();
	 
		while(rs.next()) {
			System.out.println("--------------");
			System.out.println("The id of entry is "+rs.getInt("id"));
			System.out.println("The entry is "+rs.getString("name"));
			System.out.println("The entry is "+rs.getString("email"));
			System.out.println("The entry is "+rs.getString("mobile"));
			System.out.println("The entry is "+rs.getString("department"));
			
		}
   }
 
   public static void update(String newname, int empID) throws SQLException {
	     Connection conn = DatabaseConfiguration.getConnection();
		 
	     PreparedStatement stmt = conn.prepareStatement("UPDATE employee SET name = ? WHERE id = ?");
	     stmt.setString(1, newname);  
	     stmt.setInt(2, empID);        
	     stmt.executeUpdate();
	     
	     stmt.executeUpdate();
	     
   }
   public static void delete(int empID) throws SQLException {
	   Connection conn = DatabaseConfiguration.getConnection();
	   PreparedStatement stmt = conn.prepareStatement("DELETE FROM employee WHERE id = ?");
	   stmt.setInt(1, empID);
	   stmt.executeUpdate();
   }
}

package main.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.conf.DatabaseConfiguration;
import main.dto.Employee;
import main.service.EmployeeRegistrationService;

// URL is register

public class EmployeeRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public EmployeeRegister() throws SQLException, ClassNotFoundException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
       DatabaseConfiguration.init();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// URL encoded form data
//		String name = request.getParameter("name");
//      String email = request.getParameter("email");
//      String mobile = request.getParameter("mobile");
//      String department = request.getParameter("department");
//      System.out.println(name+email+mobile+department);
//		response.getWriter().write("Hello World");
		
		
		  StringBuilder jsonBuffer = new StringBuilder();
	        BufferedReader reader = request.getReader();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            jsonBuffer.append(line);
	        }
	        String jsonData = jsonBuffer.toString();

	        // Parse JSON data
	        JSONObject jsonObject = new JSONObject(jsonData);
	        String name = jsonObject.getString("name");
	        String email = jsonObject.getString("email");
	        String mobile = jsonObject.getString("mobile");
	        String department = jsonObject.getString("department");
           
	        Employee e = new Employee(name,email,mobile,department);
	        try {
				EmployeeRegistrationService.register(e);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	      
	        // For example: Print the received data
	        System.out.println("Received data:");
	        System.out.println("Name: " + name);
	        System.out.println("Email: " + email);
	        System.out.println("Mobile: " + mobile);
	        System.out.println("Department: " + department);
	}

}

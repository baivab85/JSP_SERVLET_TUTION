package main.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.conf.DatabaseConfiguration;
import main.service.EmployeeRegistrationService;

// URL is delete_
/**
 * Servlet implementation class EmployeeDelete
 */
public class EmployeeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public EmployeeDelete() throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        DatabaseConfiguration.init();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EmployeeRegistrationService.delete(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

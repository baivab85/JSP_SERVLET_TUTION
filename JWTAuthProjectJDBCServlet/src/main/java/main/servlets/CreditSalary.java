package main.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.auth.config.AuthConfig;
import main.dto.CreditSalaryDto;
import main.services.EmployeeCreditSalaryService;
import main.services.impl.EmployeeCreditSalaryServiceImpl;



@WebServlet("/admin/credit")
public class CreditSalary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeCreditSalaryService creditService;
    private ObjectMapper objectMapper = new ObjectMapper(); 
    
    public CreditSalary() {
       creditService =  new EmployeeCreditSalaryServiceImpl();
    }
	
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreditSalaryDto creditDto = objectMapper.readValue(request.getReader(), CreditSalaryDto.class); 
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                // Validate the token
                if (!AuthConfig.isValidToken(token)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid authorization token.");
                    return;
                }

                // Check if creditDto data is valid
                if (creditDto.getId() == null || creditDto.getSalary() == null) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Invalid input data: ID or Salary is missing.");
                    return;
                }

                // Process the credit if the token is valid
                creditService.credit(creditDto.getId(), creditDto.getSalary(), token);
                response.getWriter().write("Token retrieved and processed successfully.");

            } catch (SQLException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Database error occurred: " + e.getMessage());
                e.printStackTrace(); // Replace with logging in production
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authorization token is missing or invalid.");
        }

    }

}

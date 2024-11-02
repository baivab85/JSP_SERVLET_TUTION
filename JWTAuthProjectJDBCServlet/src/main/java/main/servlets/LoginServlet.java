package main.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.auth.config.AuthConfig;
import main.db.LoginUtil;
import main.db.config.DBConfig;
import main.dto.EmployeeDto;
import main.dto.LoginDto;
import main.services.EmployeeLoginService;
import main.services.impl.EmployeeLoginServiceImpl;



 
public class LoginServlet extends HttpServlet {
	 private static Connection conn;
	private EmployeeLoginService loginService;
	private static final long serialVersionUID = 1L;
    private ObjectMapper objectMapper = new ObjectMapper(); 
	
    public LoginServlet() {
        loginService = new EmployeeLoginServiceImpl();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 LoginDto loginDto = objectMapper.readValue(request.getReader(), LoginDto.class);
	        
		 
	        String username = loginDto.getUsername();
	        String password = loginDto.getPassword();

	      
	        try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			try {
				conn = DBConfig.init();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        LoginDto employee;
			try {
				employee = LoginUtil.fetchUserCredentials(conn, username);
				if (employee != null && EmployeeLoginServiceImpl.hashPassword(password).equals(EmployeeLoginServiceImpl.hashPassword(loginDto.getPassword()))) {
			           
		            String token = AuthConfig.generateToken(employee.getUsername(), employee.getId(), employee.getRole());
		            
		           
		            response.setHeader("Authorization", "Bearer " + token);
		            response.getWriter().write("Login successful. Token: " + token);
		        } else {
		            // If authentication fails, set response status to 401
		            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		            response.getWriter().write(EmployeeLoginServiceImpl.hashPassword(password));
		            response.getWriter().write("Invalid credentials");
		            response.getWriter().write(loginDto.getPassword());
		            
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        
	

	}
}

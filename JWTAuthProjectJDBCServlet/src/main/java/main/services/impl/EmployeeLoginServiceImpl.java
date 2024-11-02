package main.services.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import main.auth.config.AuthConfig;
import main.db.LoginUtil;
import main.db.config.DBConfig;
import main.dto.LoginDto;
import main.services.EmployeeLoginService;

public class EmployeeLoginServiceImpl implements EmployeeLoginService {
	private static Connection conn;
	public static String hashPassword(String password) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Update the MessageDigest with the password bytes
            md.update(password.getBytes());

            // Complete the hash computation and get the hash bytes
            byte[] hashedBytes = md.digest();

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found!", e);
        }
    }
	@Override
	public String login(String username, String password) {
	    Connection conn = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DBConfig.init(); // Initialize DB connection
	        LoginDto loginDto = LoginUtil.fetchUserCredentials(conn, username);

	        if (loginDto != null) {
	            // Check password
	            if (hashPassword(password).equals(loginDto.getPassword())) {
	                System.out.println("Login Successful");

	                // Fetch the role from the DTO
	                String role = loginDto.getRole(); // Assuming `getRole` exists in `LoginDto`

	                // Generate token with username, user ID, and role
	                String token = AuthConfig.generateToken(loginDto.getUsername(), loginDto.getId(), role);
	                return token;
	            } else {
	                return "Invalid password";
	            }
	        } else {
	            return "User not found";
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace(); // Replace with a logger in production
	        return "Login failed due to server error";
	    } finally {
	        // Ensure the connection is closed
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	

}

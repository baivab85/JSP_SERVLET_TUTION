package main.services.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import main.db.RegisterUtil;
import main.db.config.DBConfig;
import main.dto.EmployeeDto;
import main.services.EmployeeRegisterService;

public class EmployeeRegisterServiceImpl implements EmployeeRegisterService{

	private static Connection conn;
	
	public EmployeeRegisterServiceImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DBConfig.init();
		
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	public boolean register(EmployeeDto empDto ) {
		try {
		empDto.setPassword(hashPassword(empDto.getPassword()));
		RegisterUtil.insertEmployee(conn,empDto);
		return true;
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			return false;
		}
	}

}

package main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.dto.EmployeeDto;
import main.dto.LoginDto;

public class LoginUtil {
	// replace with your DB URL
    
	 
    
	public static LoginDto fetchUserCredentials(Connection connection,String inputUsername) throws SQLException {
	LoginDto loginDto = null;
	
    String sql = "SELECT id,username, password,role FROM employee WHERE username = ?";

   
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

  
        preparedStatement.setString(1, inputUsername);

        ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
            	int fetchedId = resultSet.getInt("id");
                String fetchedUsername = resultSet.getString("username");
                String fetchedPassword = resultSet.getString("password");
                String fetchedRole = resultSet.getString("role");
                
                 loginDto = new LoginDto(fetchedId,fetchedUsername,fetchedPassword,fetchedRole);
            }
       
            return loginDto;
	}
	 
}
	




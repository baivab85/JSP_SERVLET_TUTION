package main.services.impl;

import java.sql.Connection;
import java.sql.SQLException;

import main.auth.config.AuthConfig;
import main.db.CreditSalaryUtil;
import main.db.config.DBConfig;
import main.services.EmployeeCreditSalaryService;

public class EmployeeCreditSalaryServiceImpl implements EmployeeCreditSalaryService {
 
private static Connection conn;
	
	public EmployeeCreditSalaryServiceImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DBConfig.init();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String credit(int employee_id, double salary,String token) throws SQLException {
		
		// TODO Auto-generated method stub
           if(AuthConfig.isValidToken(token)) {
        	   String result = CreditSalaryUtil.insertEmployeeSalary(conn, employee_id, salary);
        	   return result;
           }
           else {
        	   return "error";
           }
	}

}

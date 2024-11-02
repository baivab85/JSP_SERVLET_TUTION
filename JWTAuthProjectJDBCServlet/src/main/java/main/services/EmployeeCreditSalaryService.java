package main.services;

import java.sql.SQLException;

public interface EmployeeCreditSalaryService {
     String credit(int employee_id,double salary,String token) throws SQLException;
}

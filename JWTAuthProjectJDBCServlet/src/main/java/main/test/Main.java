package main.test;

import main.dto.EmployeeDto;
import main.services.impl.*;
import main.services.*;

public class Main {
    public static void main(String[] args) {
          
    	EmployeeRegisterService registerService  = new EmployeeRegisterServiceImpl();
    	
    	
        EmployeeDto employee = new EmployeeDto();
        employee.setId(1);
        employee.setName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setMobile("1234567890");
        employee.setDepartment("IT");
        employee.setUsername("johndoe");
        employee.setPassword("hashed_password_here");

        registerService.register(employee);
    }
}
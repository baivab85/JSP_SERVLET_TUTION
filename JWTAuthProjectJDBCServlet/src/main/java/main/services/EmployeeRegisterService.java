package main.services;

import main.dto.EmployeeDto;

public interface EmployeeRegisterService {
   boolean register(EmployeeDto empDto);
}

package main.servlets;


import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.dto.EmployeeDto;
import main.services.EmployeeRegisterService;
import main.services.impl.EmployeeRegisterServiceImpl;



public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    ObjectMapper objectMapper = new ObjectMapper();
    private EmployeeRegisterService registrationService;
    
	public RegistrationServlet() {
		registrationService = new EmployeeRegisterServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmployeeDto employeeDto = objectMapper.readValue(request.getReader(), EmployeeDto.class);
        System.out.println(employeeDto);
        registrationService.register(employeeDto);
	}

}

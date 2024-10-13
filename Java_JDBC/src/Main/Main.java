package Main;

import java.sql.SQLException;



public class Main {
	public static void main(String[] args) throws SQLException {
		Configuration.init();
	    Configuration.getAllEmployees();
		//Configuration.insertData(2,"Ramesh", "ramesh@gmail.com", "6574472433", "Lake Road");
		Configuration.updateEmployee(1,"Govinda");
		
	}
	
}
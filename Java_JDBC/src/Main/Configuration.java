package Main;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Configuration {

	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb", "root", "admin");
		return connection;
	}
	
	public static void createTables() throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		stmt.execute("create table if not exists employees(id bigint primary key, name varchar(50), email varchar(50), phone varchar(10), address varchar(50));");
	}
	public static void insertData(int id, String name,String email,String phone,String address) throws SQLException
	{
		Connection conn = getConnection();
	    
    	String query = "insert into employees(id,name,email,phone,address) values(?,?,?,?,?)";
    	PreparedStatement prepareStatement = conn.prepareStatement(query);
     	prepareStatement.setInt(1, id);
     	prepareStatement.setString(2, name);
     	prepareStatement.setString(3, email);
     	prepareStatement.setString(4,phone);
     	prepareStatement.setString(5,address);
    	prepareStatement.executeUpdate();
	}
	public static void getAllEmployees() throws SQLException{
		Connection conn = getConnection();
		String query = "select * from employees where id=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		 stmt.setInt(1, 3);
		 
		 ResultSet rs = stmt.executeQuery();
		 
		while(rs.next()) {
			System.out.println("--------------");
			System.out.println("The entry is "+rs.getString("name"));
			System.out.println("The id of entry is "+rs.getInt("id"));
		}
//		 
	}
	public static void updateEmployee(int empID,String newname) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		 stmt = conn.prepareStatement("UPDATE employees SET name = ? WHERE id = ?");
	     stmt.setString(1, newname);  
	     stmt.setInt(2, empID);        
	     stmt.executeUpdate();
		
	}
	
	public static void init() throws SQLException {
		createTables();
	}
	
	
	
}
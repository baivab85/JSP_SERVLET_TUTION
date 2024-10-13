package testt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing","root","admin" );
//		Statement stmt = conn.createStatement();
//		String query = "select count(name) as \"count\" from test_table;";
//		String query = "select * from test_table;";
		
		// get data - read database
//		ResultSet rs = stmt.executeQuery(query);
		
//		while(rs.next()) {
//			System.out.println("--------------");
//			System.out.println(rs.getString("name"));
//			System.out.println(rs.getInt("id"));
//		}
//		
		
//		while(rs.next()) {
//			System.out.println("--------------");
//			System.out.println(rs.getString("count"));
////			System.out.println(rs.getInt("id"));
//		}
		
		
//		write in database
//		String name = "Saina";
//		int id = 8;
//		String insertQuery = "INSERT INTO test_table (name, id) VALUES (?, ?)";
//		PreparedStatement stmt = conn.prepareStatement(insertQuery);
//		stmt.setInt(2, id);
//		stmt.setString(1, name);
////		String query = "insert into test_table(name,id) values("+name+","+id+");";
////		String query = "insert into test_table values("+name+", 6);";
//		
//		int execute = stmt.executeUpdate();
//		System.out.println(execute);
		
		
//		String name  = "Baivab";
//		int id = 9;
//		
//		String query = "insert into test_table(name,id) values(?, ?)";
//		
//		 PreparedStatement prepareStatement = conn.prepareStatement(query);
//		 prepareStatement.setString(1, name);
//		 prepareStatement.setInt(2, id);
//		 prepareStatement.executeUpdate();
		 
		
		
//		prepareStatement - get operation
		
		
//		 String query = "select * from test_table where id=?";
//		 PreparedStatement stmt = conn.prepareStatement(query);
//		 stmt.setInt(1, 6);
//		 ResultSet rs = stmt.executeQuery();
//		 
//		while(rs.next()) {
//			System.out.println("--------------");
//			System.out.println(rs.getString("name"));
//			System.out.println(rs.getInt("id"));
//		}
//		 
		 
		
		
		
//		update operation
		
//		PreparedStatement stmt = conn.prepareStatement("update test_table set name=? where id=?");
//		stmt.setString(1, "Tarun");
//		stmt.setInt(2, 6);
//		 
//		stmt.executeUpdate();
		
		
		
		
	}
}
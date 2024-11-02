package main.dto;

public class LoginDto {
   private int id;
   private String username;
   private String password;
   private String role;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public LoginDto() {
	super();
	// TODO Auto-generated constructor stub
}
public void setUsername(String username) {
	this.username = username;
}
public LoginDto(int id, String username, String password, String role) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.role = role;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
   
}

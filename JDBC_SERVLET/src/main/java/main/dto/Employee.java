package main.dto;

public class Employee {

	   private String name;
	    private String email;
	    private String mobile;
	    private String department;

	    // Constructor
	    public Employee(String name, String email, String mobile, String department) {
	        this.name = name;
	        this.email = email;
	        this.mobile = mobile;
	        this.department = department;
	    }

	    // Getter and Setter for name
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    // Getter and Setter for email
	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    // Getter and Setter for mobile
	    public String getMobile() {
	        return mobile;
	    }

	    public void setMobile(String mobile) {
	        this.mobile = mobile;
	    }

	    // Getter and Setter for department
	    public String getDepartment() {
	        return department;
	    }

	    public void setDepartment(String department) {
	        this.department = department;
	    }

		@Override
		public String toString() {
			return "Employee [name=" + name + ", email=" + email + ", mobile=" + mobile + ", department=" + department
					+ "]";
		}

	    
	    
}

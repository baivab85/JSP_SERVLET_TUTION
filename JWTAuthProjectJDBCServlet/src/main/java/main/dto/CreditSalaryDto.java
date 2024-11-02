package main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditSalaryDto {

    private Integer employeeId;
    private Double salary;

    // Default constructor
    public CreditSalaryDto() {
        super();
    }

    // Parameterized constructor
    public CreditSalaryDto(Integer employeeId, Double salary) {
        this.employeeId = employeeId;
        this.salary = salary;
    }

    // Getters and Setters
    @JsonProperty("employeeId")
    public Integer getId() {
        return employeeId;
    }
    @JsonProperty("employeeId")
    public void setId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    // Override toString for easier logging and debugging
    @Override
    public String toString() {
        return "CreditSalaryDto{" +
                "employeeId=" + employeeId +
                ", salary=" + salary +
                '}';
    }
}

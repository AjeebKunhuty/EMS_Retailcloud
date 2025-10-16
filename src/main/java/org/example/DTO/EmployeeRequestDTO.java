package org.example.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeRequestDTO {
    private String name;
    private LocalDate dob;
    private BigDecimal salary;
    private Integer department;
    private String address;
    private String role;
    private LocalDate joining_date;
    private BigDecimal bonus_percentage;
    private Integer manager;
    
    public EmployeeRequestDTO(String name, LocalDate dob, BigDecimal salary,
                              Integer department, String address, String role,
                              LocalDate joining_date, BigDecimal bonus_percentage, Integer manager) {
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.department = department;
        this.address = address;
        this.role = role;
        this.joining_date = joining_date;
        this.bonus_percentage = bonus_percentage;
        this.manager = manager;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public Integer getDepartment() { return department; }
    public void setDepartment(Integer department) { this.department = department; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDate getJoining_date() { return joining_date; }
    public void setJoining_date(LocalDate joining_date) { this.joining_date = joining_date; }

    public BigDecimal getBonus_percentage() { return bonus_percentage; }
    public void setBonus_percentage(BigDecimal bonus_percentage) { this.bonus_percentage = bonus_percentage; }

    public Integer getManager() { return manager; }
    public void setManager(Integer manager) { this.manager = manager; }
}

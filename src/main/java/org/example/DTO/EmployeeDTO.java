package org.example.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDTO {
    private Integer id;
    private String name;
    private LocalDate dob;
    private BigDecimal salary;
    private String department;
    private String address;
    private String role;
    private LocalDate joiningDate;
    private BigDecimal bonusPercentage;
    private String manager;

    public EmployeeDTO(Integer id, String name, LocalDate dob, BigDecimal salary, String department, String address, String role, LocalDate joiningDate, BigDecimal bonusPercentage, String manager){
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.department = department;
        this.address = address;
        this.role = role;
        this.joiningDate = joiningDate;
        this.bonusPercentage = bonusPercentage;
        this.manager = manager;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public LocalDate getDob() { return dob; }
    public BigDecimal getSalary() { return salary; }
    public String getDepartment() { return department; }
    public String getAddress() { return address; }
    public String getRole() { return role; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public BigDecimal getBonusPercentage() { return bonusPercentage; }
    public String getManager() { return manager; }
}

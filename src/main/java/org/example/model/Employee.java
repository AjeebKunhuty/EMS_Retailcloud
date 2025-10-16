package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private LocalDate dob;

    @Column(precision = 15, scale = 2)
    private BigDecimal salary;

    private String address;
    private String role;
    private LocalDate joining_date;

    @Column(precision = 5, scale = 2)
    private BigDecimal bonus_percentage;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @OneToOne
    @JoinColumn(name = "manager")
    private Employee manager;

    public int getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDate getJoining_date() { return joining_date; }
    public void setJoining_date(LocalDate joining_date) { this.joining_date = joining_date; }

    public BigDecimal getBonus_percentage() { return bonus_percentage; }
    public void setBonus_percentage(BigDecimal bonus_percentage) { this.bonus_percentage = bonus_percentage; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Employee getManager() { return manager; }
    public void setManager(Employee manager) { this.manager = manager; }
}

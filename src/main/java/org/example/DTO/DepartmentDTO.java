package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDTO {
    private Integer id;
    private String name;
    private LocalDate creation_date;
    private String head;
    private List<EmployeeDTO> employeeDTOList;

    public DepartmentDTO(Integer id, String name, LocalDate creation_date, String head){
        this.id = id;
        this.name = name;
        this.creation_date = creation_date;
        this.head = head;
    }

    public DepartmentDTO(Integer id, String name, LocalDate creation_date, String head, List<EmployeeDTO> employeeDTOList){
        this.id = id;
        this.name = name;
        this.creation_date = creation_date;
        this.head = head;
        this.employeeDTOList = employeeDTOList;
    }

    public String getName() { return name; }
    public Integer getId() { return id; }
    public LocalDate getCreation_date() { return creation_date; }
    public String getHead() { return head; }
    public List<EmployeeDTO> getEmployeeDTOList() { return employeeDTOList; }
}

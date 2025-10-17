package org.example.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReportingChainDTO {
    private Integer id;
    private String name;
    private String department;
    private String role;
    private List<String> managerChain;

    public ReportingChainDTO(Integer id, String name, String department, String role, List<String> managerChain){
        this.id = id;
        this.name = name;
        this.department = department;
        this.role = role;
        this.managerChain = managerChain;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    public List<String> getManagerChain() { return managerChain; }
}

package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDTO {
    private Integer id;
    private String name;
    private LocalDate creationDate;
    private String head;
    private List<EmployeeDTO> employeeList;
    private Integer employeeCount;
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal avg;

    public DepartmentDTO(Integer id, String name, LocalDate creationDate, String head, List<EmployeeDTO> employeeList, Integer employeeCount, BigDecimal min, BigDecimal max, BigDecimal avg){
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.head = head;
        this.employeeList = employeeList;
        this.employeeCount = employeeCount;
        this.max =  max != null ? max.setScale(2, RoundingMode.HALF_UP) : null;
        this.min = min != null ? min.setScale(2, RoundingMode.HALF_UP) : null;
        this.avg = avg != null ? avg.setScale(2, RoundingMode.HALF_UP) : null;
    }


    public String getName() { return name; }
    public Integer getId() { return id; }
    public LocalDate getCreationDate() { return creationDate; }
    public String getHead() { return head; }
    public List<EmployeeDTO> getEmployeeList() { return employeeList; }
    public Integer getEmployeeCount() { return employeeCount; }
    public BigDecimal getMax() { return max; }
    public BigDecimal getAvg() { return avg; }
    public BigDecimal getMin() { return min; }
}

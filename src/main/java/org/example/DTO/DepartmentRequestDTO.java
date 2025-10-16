package org.example.DTO;

import java.time.LocalDate;

public class DepartmentRequestDTO {
    private Integer id;
    private String name;
    private LocalDate creation_date;
    private Integer head;

    public DepartmentRequestDTO(Integer id, String name, LocalDate creation_date, Integer head){
        this.id = id;
        this.name = name;
        this.creation_date = creation_date;
        this.head = head;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getCreation_date() { return creation_date; }
    public void setCreation_date(LocalDate creation_date) { this.creation_date = creation_date; }

    public Integer getHead() { return head; }
    public void setHead(Integer head) { this.head = head; }
}

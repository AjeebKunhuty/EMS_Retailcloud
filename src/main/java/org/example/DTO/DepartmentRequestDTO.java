package org.example.DTO;

import java.time.LocalDate;

public class DepartmentRequestDTO {
    private Integer id;
    private String name;
    private LocalDate creationDate;
    private Integer head;

    public DepartmentRequestDTO(Integer id, String name, LocalDate creationDate, Integer head){
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.head = head;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    public Integer getHead() { return head; }
    public void setHead(Integer head) { this.head = head; }
}

package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "head")
    private Employee head;

    public int getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getCreationDate(){ return creationDate; }
    public void setCreationDate(LocalDate date) { this.creationDate = date; }

    public Employee getHead() { return head; }
    public void setHead(Employee head) { this.head = head; }
}

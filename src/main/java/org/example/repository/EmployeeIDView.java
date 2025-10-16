package org.example.repository;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "id" })
public interface EmployeeIDView {
    String getName();
    Integer getId();
}

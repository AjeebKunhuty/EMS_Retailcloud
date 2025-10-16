package org.example.repository;

import org.example.DTO.EmployeeDTO;
import org.example.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    int countByDepartmentId(Integer id);
    List<Employee> findByDepartmentId(Integer id);
    Page<EmployeeIDView> findAllProjectedBy(Pageable pageable);
}

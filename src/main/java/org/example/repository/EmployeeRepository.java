package org.example.repository;

import org.example.DTO.EmployeeDTO;
import org.example.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Integer countByDepartmentId(Integer id);
    List<Employee> findByDepartmentId(Integer id);
    Page<EmployeeBasicView> findAllBasicProjectedBy(Pageable pageable);

    Employee findTopSalaryByDepartmentIdOrderBySalaryDesc(Integer id);
    Employee findTopSalaryByDepartmentIdOrderBySalary(Integer id);
    @Query(value = """
            Select avg(salary) from employee where department = :id
            """,nativeQuery = true)
    BigDecimal avgSalaryByDepartmentId(Integer id);
    List<Employee> findByOrderByJoiningDate();

}

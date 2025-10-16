package org.example.service;

import org.example.DTO.DepartmentDTO;
import org.example.DTO.DepartmentRequestDTO;
import org.example.DTO.EmployeeDTO;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Department add(DepartmentRequestDTO dep){
        Department department = new Department();
        department.setId(null);
        department.setName(dep.getName());
        department.setCreation_date(dep.getCreation_date());

        Employee head = employeeRepository.findById(dep.getHead()).orElseThrow(()->
                new RuntimeException("Department head not found"));
        department.setHead(head);

        return departmentRepository.save(department);
    }

    @Transactional
    public DepartmentDTO update(Integer id, DepartmentRequestDTO department){
        Department dep = getById(id);
        dep.setName(department.getName());
        dep.setCreation_date(department.getCreation_date());
        Employee head = employeeRepository.findById(department.getHead()).orElseThrow(()->
                new RuntimeException("Department head not found"));
        dep.setHead(head);
        departmentRepository.save(dep);
        return new DepartmentDTO(
                dep.getId(),
                dep.getName(),
                dep.getCreation_date(),
                dep.getHead() != null ? dep.getHead().getName() : null
        );
    }

    @Transactional
    public void delete(Integer id){
        try{
            int count = employeeRepository.countByDepartmentId(id);
            if(count > 0) throw new SQLIntegrityConstraintViolationException();
            departmentRepository.deleteById(id);
        }catch (SQLIntegrityConstraintViolationException e){
            throw new RuntimeException("Cannot delete department with employees assigned");
        }
    }

    @Transactional(readOnly = true)
    public Department getById(Integer id){
        return departmentRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Department not found with id: " + id.toString()));
    }

    @Transactional
    public Map<String, Object> getEmployees(int page, int size, String expand){
        if("employee".equals(expand)) {
            PageRequest paging = PageRequest.of(page, size);
            Page<Department> pageDepartments = departmentRepository.findAll(paging);
            List<DepartmentDTO> dtoList = pageDepartments.getContent().stream()
                    .map(dept -> new DepartmentDTO(
                            dept.getId(),
                            dept.getName(),
                            dept.getCreation_date(),
                            dept.getHead() != null ? dept.getHead().getName() : null,
                            employeeRepository.findByDepartmentId(dept.getId()).stream()
                                    .map(emp -> new EmployeeDTO(
                                            emp.getId(),
                                            emp.getName(),
                                            emp.getDob(),
                                            emp.getSalary(),
                                            emp.getDepartment() != null ? emp.getDepartment().getName() : null,
                                            emp.getAddress(),
                                            emp.getRole(),
                                            emp.getJoining_date(),
                                            emp.getBonus_percentage(),
                                            emp.getManager() != null ? emp.getManager().getName() : null
                                    )).collect(Collectors.toList())
                    )).collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("departments", dtoList);
            response.put("currentPage", pageDepartments.getNumber());
            response.put("totalPages", pageDepartments.getTotalPages());
            return response;
        } else {
            PageRequest paging = PageRequest.of(page,size);
            Page<Department> pageDepartments = departmentRepository.findAll(paging);
            List<DepartmentDTO> dtoList = pageDepartments.getContent().stream()
                    .map(emp -> new DepartmentDTO(
                            emp.getId(),
                            emp.getName(),
                            emp.getCreation_date(),
                            emp.getHead() != null ? emp.getHead().getName() : null
                    )).collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("departments", dtoList);
            response.put("currentPage", pageDepartments.getNumber());
            response.put("totalPages", pageDepartments.getTotalPages());
            return response;
        }
    }
}

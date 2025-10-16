package org.example.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.DTO.DepartmentDTO;
import org.example.DTO.EmployeeDTO;
import org.example.DTO.EmployeeRequestDTO;
import org.example.DTO.PagedResponse;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeIDView;
import org.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Transactional
    public Employee add(EmployeeRequestDTO employee){
        Employee emp = new Employee();
        emp.setId(null);
        emp.setName(employee.getName());
        emp.setDob(employee.getDob());
        emp.setSalary(employee.getSalary());
        emp.setAddress(employee.getAddress());
        emp.setRole(employee.getRole());
        emp.setJoining_date(employee.getJoining_date());
        emp.setBonus_percentage(employee.getBonus_percentage());

        Department dept = departmentRepository.findById(employee.getDepartment()).orElseThrow(() -> new RuntimeException(
                "Department not found with id: "+employee.getDepartment().toString()));;
        emp.setDepartment(dept);

        emp.setManager(getById(employee.getManager()));

        return employeeRepository.save(emp);
    }

    @Transactional
    public EmployeeDTO update(Integer id, EmployeeRequestDTO employee){
        Employee emp = getById(id);
        emp.setName(employee.getName());
        emp.setDob(employee.getDob());
        emp.setSalary(employee.getSalary());
        emp.setAddress(employee.getAddress());
        emp.setRole(employee.getRole());
        emp.setJoining_date(employee.getJoining_date());
        emp.setBonus_percentage(employee.getBonus_percentage());
        Department dept = departmentRepository.findById(employee.getDepartment()).orElseThrow(() -> new RuntimeException(
                "Department not found with id: "+employee.getDepartment().toString()));;
        emp.setDepartment(dept);

        emp.setManager(getById(employee.getManager()));
        employeeRepository.save(emp);
        return new EmployeeDTO(
                emp.getId(),
                emp.getName(),
                emp.getDob(),
                emp.getSalary(),
                emp.getDepartment().getName(),
                emp.getAddress(),
                employee.getRole(),
                emp.getJoining_date(),
                emp.getBonus_percentage(),
                emp.getManager() != null ? emp.getManager().getName() : null
        );
    }

    public Employee getById(Integer id){
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Employee not found with id: "+id.toString()));
    }

    @Transactional
    public String transfer(Integer empId, Integer deptId){
        Employee emp = getById(empId);
        Department dept = departmentRepository.findById(deptId).orElseThrow(() -> new RuntimeException(
                "Department not found with id: "+deptId.toString()));;
        emp.setDepartment(dept);
        return employeeRepository.save(emp).getName() +" moved to " +emp.getDepartment().getName();
    }

    @Transactional(readOnly = true)
    public Map<String, Object>  getAll(int page, int size){
        PageRequest paging = PageRequest.of(page,size);
        Page<Employee> pageEmployees = employeeRepository.findAll(paging);
        List<EmployeeDTO> dtoList = pageEmployees.getContent().stream()
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
                )).collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("employees", dtoList);
        response.put("currentPage", pageEmployees.getNumber());
        response.put("totalPages", pageEmployees.getTotalPages());
        response.put("totalElements", pageEmployees.getTotalElements());
        return response;
    }

    public Map<String,Object> getFull(int page,int size){
        Map<String,Object> map = getAll(page,size);
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
        response.put("totalElements", pageDepartments.getTotalElements());
        map.putAll(response);
        return map;
    }

    //@JsonIgnoreProperties({"pageable", "sort"})
    @Transactional(readOnly = true)
    public Map<String, Object> getEmployeeIds(int page, int size){
        PageRequest paging = PageRequest.of(page,size);
        Page<EmployeeIDView> pageEmployees = employeeRepository.findAllProjectedBy(paging);
        List<EmployeeIDView> content = pageEmployees.getContent();
        Map<String, Object> response = new HashMap<>();
        PagedResponse<EmployeeIDView> paged = new PagedResponse<>(
                content,
                pageEmployees.getNumber(),
                pageEmployees.getTotalPages()
        );
        response.put("employees", paged.getContent());
        response.put("currentPage", paged.getCurrentPage());
        response.put("totalPages", paged.getTotalPages());

        return response;
    }
}

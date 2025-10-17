package org.example.service;

import org.example.DTO.*;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeBasicView;
import org.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        emp.setJoiningDate(employee.getJoiningDate());
        emp.setBonusPercentage(employee.getBonusPercentage());

        Department dept = departmentRepository.findById(employee.getDepartment()).orElseThrow(() -> new RuntimeException(
                "Department not found with id: "+employee.getDepartment().toString()));;
        emp.setDepartment(dept);

        emp.setManager(getById(employee.getManager()));

        return employeeRepository.save(emp);
    }

    @Transactional
    public EmployeeDTO update(Integer id, EmployeeRequestDTO employee){
        Employee emp = getById(id);
        emp.setName(employee.getName() != null ? employee.getName() : emp.getName());
        emp.setDob(employee.getDob() != null ? employee.getDob() : emp.getDob());
        emp.setSalary(employee.getSalary() != null ? employee.getSalary() : emp.getSalary());
        emp.setAddress(employee.getAddress() != null ? employee.getAddress() : emp.getAddress());
        emp.setRole(employee.getRole() != null ? employee.getRole() : emp.getRole());
        emp.setJoiningDate(employee.getJoiningDate() != null ? employee.getJoiningDate() : emp.getJoiningDate());
        emp.setBonusPercentage(employee.getBonusPercentage() != null ? employee.getBonusPercentage() : emp.getBonusPercentage());

        emp.setDepartment(employee.getDepartment() != null ? departmentRepository.findById(employee.getDepartment()).orElseThrow(() -> new RuntimeException(
                "Department not found with id: "+employee.getDepartment().toString())) : emp.getDepartment());

        emp.setManager(employee.getManager() != null ? getById(employee.getManager()) : emp.getManager());
        employeeRepository.save(emp);
        return new EmployeeDTO(
                emp.getId(),
                emp.getName(),
                emp.getDob(),
                emp.getSalary(),
                emp.getDepartment() != null ? emp.getDepartment().getName() : null,
                emp.getAddress(),
                emp.getRole(),
                emp.getJoiningDate(),
                emp.getBonusPercentage(),
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
                        emp.getJoiningDate(),
                        emp.getBonusPercentage(),
                        emp.getManager() != null ? emp.getManager().getName() : null
                )).collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("employees", dtoList);
        response.put("currentPage", pageEmployees.getNumber());
        response.put("totalPages", pageEmployees.getTotalPages());
        return response;
    }

    public List<Map<String,Object>> getFull(int page,int size){
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String,Object> map = getAll(page,size);
        mapList.add(map);
        PageRequest paging = PageRequest.of(page,size);
        Page<Department> pageDepartments = departmentRepository.findAll(paging);
        List<DepartmentDTO> dtoList = pageDepartments.getContent().stream()
                .map(emp -> new DepartmentDTO(
                        emp.getId(),
                        emp.getName(),
                        emp.getCreationDate(),
                        emp.getHead() != null ? emp.getHead().getName() : null,
                        null,
                        null,
                        null,
                        null,
                        null
                )).collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("departments", dtoList);
        response.put("currentPage", pageDepartments.getNumber());
        response.put("totalPages", pageDepartments.getTotalPages());
        mapList.add(response);
        return mapList;
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getEmployeeIds(int page, int size, boolean lookup){
        PageRequest paging = PageRequest.of(page,size);
        if(lookup) {
            Page<EmployeeBasicView> pageEmployees = employeeRepository.findAllBasicProjectedBy(paging);
            List<EmployeeBasicView> content = pageEmployees.getContent();
            Map<String, Object> response = new HashMap<>();
            PagedResponse<EmployeeBasicView> paged = new PagedResponse<>(
                    content,
                    pageEmployees.getNumber(),
                    pageEmployees.getTotalPages()
            );
            response.put("employees", paged.getContent());
            response.put("currentPage", paged.getCurrentPage());
            response.put("totalPages", paged.getTotalPages());
            return response;
        } else {
            return getAll(page, size);
        }
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getReportingChain(int page, int size) {
        List<String> chain = new ArrayList<>();
        PageRequest paging = PageRequest.of(page,size);
        Page<Employee> pageEmployees = employeeRepository.findAll(paging);
        List<ReportingChainDTO> dtoList = pageEmployees.getContent().stream()
                .map(emp -> new ReportingChainDTO(
                        emp.getId(),
                        emp.getName(),
                        emp.getDepartment() != null ? emp.getDepartment().getName() : null,
                        emp.getRole(),
                        emp.getManager() != null ? buildChain(emp) : null
                )).collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("employees", dtoList);
        response.put("currentPage", pageEmployees.getNumber());
        response.put("totalPages", pageEmployees.getTotalPages());
        response.put("totalElements", pageEmployees.getTotalElements());
        return response;
    }

    public List<String> buildChain(Employee employee){
        List<String> chain = new ArrayList<>();
        Employee manager = employee.getManager();
        while(manager != null){
            if(chain.contains(manager.getName()))  //stopping cycles
                break;
            chain.add(manager.getName());
            manager = manager.getManager();
        }
        return chain;
    }

    public Map<String, Object> getLongestServer(int page, int size) {
        PageRequest paging = PageRequest.of(page,size, Sort.by("joiningDate"));
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
                    emp.getJoiningDate(),
                    emp.getBonusPercentage(),
                    emp.getManager() != null ? emp.getManager().getName() : null
                )).collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("employees", dtoList);
        response.put("currentPage", pageEmployees.getNumber());
        response.put("totalPages", pageEmployees.getTotalPages());
        return response;
    }

    public String resign(Integer empId) {
        Employee employee = getById(empId);
        employee.setDepartment(null);
        return  employeeRepository.save(employee).getName() +" department set to null";
    }
}

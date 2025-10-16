package org.example.controller;

import org.example.DTO.EmployeeDTO;
import org.example.DTO.EmployeeRequestDTO;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.service.DepartmentService;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<String> createEmployee(
            @RequestBody EmployeeRequestDTO employee ){
        return ResponseEntity.ok(employeeService.add(employee).getName() + " added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Integer id,
            @RequestBody EmployeeRequestDTO employee){
        return ResponseEntity.ok(employeeService.update(id, employee));
    }

    @PostMapping("/{empId}/transfer")
    public ResponseEntity<String> transferEmployee(
            @PathVariable Integer empId,
            @RequestParam Integer deptId ){
        return ResponseEntity.ok(employeeService.transfer(empId, deptId));
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
         return ResponseEntity.ok(employeeService.getAll(page, size));
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getEmployeeNameId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) boolean lookup){
        return ResponseEntity.ok(employeeService.getEmployeeIds(page,size));
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getFullDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        return ResponseEntity.ok(employeeService.getFull(page,size));
    }

}

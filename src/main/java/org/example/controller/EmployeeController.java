package org.example.controller;

import org.example.DTO.EmployeeDTO;
import org.example.DTO.EmployeeRequestDTO;
import org.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/{empId}/exit")
    public ResponseEntity<String> resignEmployee(
            @PathVariable Integer empId){
        return ResponseEntity.ok(employeeService.resign(empId));
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getEmployeeNameId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) boolean lookup){
        return ResponseEntity.ok(employeeService.getEmployeeIds(page,size,lookup));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getFullDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        return ResponseEntity.ok(employeeService.getFull(page,size));
    }

    @GetMapping("/chain")
    public ResponseEntity<Map<String, Object>> getReportingChain(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(employeeService.getReportingChain(page, size));
    }

    @GetMapping("/longserving")
    public ResponseEntity<Map<String, Object>> getLongestServer(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(employeeService.getLongestServer(page, size));
    }
}

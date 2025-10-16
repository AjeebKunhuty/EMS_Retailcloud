package org.example.controller;

import org.example.DTO.DepartmentDTO;
import org.example.DTO.DepartmentRequestDTO;
import org.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<String> addDepartment(@RequestBody DepartmentRequestDTO department){
        return ResponseEntity.ok(departmentService.add(department).getName() + " added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(
            @PathVariable Integer id,
            @RequestBody DepartmentRequestDTO department){
        return ResponseEntity.ok(departmentService.update(id, department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Integer id){
        departmentService.delete(id);
        return ResponseEntity.ok("Department "+id+" deleted successfully");
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String expand){
        return ResponseEntity.ok(departmentService.getEmployees(page, size, expand));
    }

}

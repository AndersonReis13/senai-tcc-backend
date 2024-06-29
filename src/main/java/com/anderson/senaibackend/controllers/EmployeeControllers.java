package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.Employee;
import com.anderson.senaibackend.dto.EmployeeDto;
import com.anderson.senaibackend.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "cos/employee")
public class EmployeeControllers {

    public final EmployeeService employeeService;

    public EmployeeControllers(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(employeeService.findById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok().body(employeeService.findAll());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(dto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeDto dto){
        return ResponseEntity.ok().body(employeeService.updateEmployee(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

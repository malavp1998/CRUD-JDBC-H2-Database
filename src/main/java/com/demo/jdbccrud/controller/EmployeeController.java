package com.demo.jdbccrud.controller;

import com.demo.jdbccrud.model.Employee;
import com.demo.jdbccrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllPersons() {
        return this.employeeService.getEmployees();
    }


    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") final int id) {
        if (this.employeeService.getEmployeeById(id) == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(this.employeeService.getEmployeeById(id), HttpStatus.OK);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") final int id) {
        try {
            this.employeeService.deleteEmployee(id);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employee")
    public void saveEmployee(@RequestBody final Employee employee) {
        this.employeeService.saveEmployee(employee);
    }

    @PutMapping("/employee/{id}")
    public void updateEmployee(@RequestBody final Employee employee, @PathVariable final int id) {
        this.employeeService.updateEmployee(employee, id);
    }
}

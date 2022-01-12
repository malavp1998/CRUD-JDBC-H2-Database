package com.demo.jdbccrud.controller;

import com.demo.jdbccrud.model.Employee;
import com.demo.jdbccrud.repository.EmployeeRepository;
import com.demo.jdbccrud.service.EmployeeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")

    private List<Employee> getAllPersons() {
        return employeeService.getEmployees();
    }


    @GetMapping("/employee/{id}")
    private ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
        if (employeeService.getEmployeeById(id) == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
        }
    }


    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/employee")
    public void saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
    }


    @PutMapping("/employee/{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
        employeeService.updateEmployee(employee, id);
    }
}

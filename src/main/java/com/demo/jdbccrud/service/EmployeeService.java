package com.demo.jdbccrud.service;

import com.demo.jdbccrud.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee getEmployeeById(int id);

    void saveEmployee(Employee employee);

    void deleteEmployee(int id);

    void updateEmployee(Employee employee, int id);
}

package com.demo.jdbccrud.service.implementation;

import com.demo.jdbccrud.model.Employee;
import com.demo.jdbccrud.repository.EmployeeRepository;
import com.demo.jdbccrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(final int id) {
        return this.employeeRepository.findById(id);
    }

    @Override
    public void saveEmployee(final Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(final int id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public void updateEmployee(final Employee employee, final int id) {
        this.employeeRepository.update(employee, id);
    }


}

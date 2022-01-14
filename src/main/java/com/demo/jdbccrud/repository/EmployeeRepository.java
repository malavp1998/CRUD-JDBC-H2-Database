package com.demo.jdbccrud.repository;

import com.demo.jdbccrud.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class EmployeeRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EmployeeRepository() {
    }

    public List<Employee> findAll() {
        final List<Employee> items = this.jdbcTemplate.query("select id, firstname, lastname, email from employee", (result, rowNum) -> new Employee(result.getInt("id"), result.getString("firstname"),
                result.getString("lastname"), result.getString("email")));
        return items;
    }

    public Employee findById(final int id) {
        final List<Employee> employees = this.jdbcTemplate.query("select id, firstname, lastname, email from employee where employee.id=" + id, (result, rowNum) -> new Employee(result.getInt("id"), result.getString("firstname"),
                result.getString("lastname"), result.getString("email")));
        log.info("find by id item size " + employees.size());
        if (employees.size() == 0) {
            return null;
        } else {
            return employees.get(0);
        }
    }

    public void save(final Employee employee) {
        final String insertQuery = "insert into Employee (firstName, lastName, email) values (?, ?, ?)";
        final int update = this.jdbcTemplate.update(insertQuery, employee.getFirstName(), employee.getLastName(), employee.getEmail());

        if (update == 1) {
            log.info("employee saved");
        } else {
            log.info("employee already present");
        }
    }

    public void deleteById(final int id) {
        final String deleteQuery = "delete from employee where employee.id = (?)";
        final int update = this.jdbcTemplate.update(deleteQuery, id);
        try {
            if (update == 1) {
                log.info("employees Deleted " + id);
            } else {
                log.info("Error in Deleting " + id);
                throw new RuntimeException("Employee not present with id = " + id);
            }
        } catch (final Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public void update(final Employee employee, final int id) {
        final String updateQuery = "update employee set firstName = ?, lastName = ?, email = ? where employee.id = ?";
        final int update = this.jdbcTemplate.update(updateQuery, employee.getFirstName(), employee.getLastName(), employee.getEmail(), id);
        if (update == 1) {
            log.info(String.format("employee %d", id));
        } else {
            log.info("Error in updating");
        }
    }
}

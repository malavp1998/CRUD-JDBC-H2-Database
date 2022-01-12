package com.demo.jdbccrud.repository;

import com.demo.jdbccrud.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Employee> findAll() {
        List<Employee> items = jdbcTemplate.query("select id, firstname,lastname, email from employee", (result, rowNum) -> new Employee(result.getInt("id"), result.getString("firstname"),
                result.getString("lastname"), result.getString("email")));
        return items;
    }

    public Employee findById(int id) {
        List<Employee> items = jdbcTemplate.query("select id, firstname,lastname, email from employee where employee.id=" + id, (result, rowNum) -> new Employee(result.getInt("id"), result.getString("firstname"),
                result.getString("lastname"), result.getString("email")));
        System.out.println(items);
        return items.get(0);
    }

    public void save(Employee employee) {
        String insertQuery = "insert into Employee (firstName, lastName, email) values (?, ?, ?)";
        jdbcTemplate.update(insertQuery, employee.getFirstName(), employee.getLastName(), employee.getEmail());
        System.out.println("employees saved");
    }

    public void deleteById(int id) {
        String deleteQuery = "delete from employee where employee.id = (?)";
        int update = jdbcTemplate.update(deleteQuery, id);
        if (update == 1) {
            System.out.println("employees Deleted");
        } else {
            System.out.println("Error in Deleting");
        }
    }


    public void update(Employee employee, int id) {
        String updateQuery = "update employee set firstName = ?, lastName = ?, email = ? where employee.id = ?";
        int update = jdbcTemplate.update(updateQuery, employee.getFirstName(), employee.getLastName(), employee.getEmail(), id);
        if (update == 1) {
            System.out.println("updated");
        } else {
            System.out.println("Error in updating");
        }
    }
}

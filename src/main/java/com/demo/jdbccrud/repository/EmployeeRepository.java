package com.demo.jdbccrud.repository;

import com.demo.jdbccrud.model.Employee;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class EmployeeRepository {

    //  Logger logger = (Logger) LoggerFactory.getLogger(EmployeeRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public EmployeeRepository() {
    }

    public List<Employee> findAll() {
        List<Employee> items = jdbcTemplate.query("select id, firstname,lastname, email from employee", (result, rowNum) -> new Employee(result.getInt("id"), result.getString("firstname"),
                result.getString("lastname"), result.getString("email")));
        return items;
    }

    public Employee findById(int id) {
        List<Employee> employees = jdbcTemplate.query("select id, firstname,lastname, email from employee where employee.id=" + id, (result, rowNum) -> new Employee(result.getInt("id"), result.getString("firstname"),
                result.getString("lastname"), result.getString("email")));
        //  logger.info("find by id item size "+ employees.size());
        if (employees.size() == 0) {
            return null;
        } else {
            return employees.get(0);
        }
    }

    public void save(Employee employee) {
        String insertQuery = "insert into Employee (firstName, lastName, email) values (?, ?, ?)";
        int update = jdbcTemplate.update(insertQuery, employee.getFirstName(), employee.getLastName(), employee.getEmail());

        if (update == 1) {
            //   logger.info("employee saved");
        } else {
            //    logger.info("employee already present");
        }
    }

    public void deleteById(int id) {
        String deleteQuery = "delete from employee where employee.id = (?)";
        int update = jdbcTemplate.update(deleteQuery, id);
        try {
            if (update == 1) {
                //    logger.info("employees Deleted");
            } else {
                //    logger.info("Error in Deleting");
                throw new RuntimeException("Employee not present with id = " + id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void update(Employee employee, int id) {
        String updateQuery = "update employee set firstName = ?, lastName = ?, email = ? where employee.id = ?";
        int update = jdbcTemplate.update(updateQuery, employee.getFirstName(), employee.getLastName(), employee.getEmail(), id);
        if (update == 1) {
            //  logger.info("updated");
        } else {
            //   logger.info("Error in updating");
        }
    }
}

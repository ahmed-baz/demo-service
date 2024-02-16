package org.demo.app.repo;

import org.demo.app.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends MainRepo<Employee> {

    Optional<Employee> findByEmail(String email);

}

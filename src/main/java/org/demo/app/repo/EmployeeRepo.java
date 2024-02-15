package org.demo.app.repo;

import org.demo.app.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends MainRepo<Employee> {
}

package org.demo.app.repo;

import org.demo.app.model.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends MainRepo<EmployeeEntity> {

    Optional<EmployeeEntity> findByEmail(String email);

}

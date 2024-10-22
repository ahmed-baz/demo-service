package org.demo.app.repo;


import org.demo.app.model.EmployeeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@SpringBootTest
class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Test
    @Sql(statements = "insert into employees(id, first_name, last_name, email,salary) values (5666, 'Ahmed','Ali','ahmed.ali.c@stc.com.sa',10000)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from employees where id=5666", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testFindByEmail() {
        Optional<EmployeeEntity> employee = employeeRepo.findByEmail("ahmed.ali.c@stc.com.sa");
        Assertions.assertTrue(employee.isPresent());
    }

}

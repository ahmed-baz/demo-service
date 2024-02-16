package org.demo.app.service;


import lombok.extern.log4j.Log4j2;
import org.demo.app.dto.EmployeeDto;
import org.demo.app.model.Employee;
import org.demo.app.repo.EmployeeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Log4j2
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTests {

    @MockBean
    private EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeService employeeService;
    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = Employee.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Ahmed")
                .lastName("Ali")
                .email("ahmed.ali@gmail.com")
                .salary(new BigDecimal(15000))
                .joinDate(LocalDate.now())
                .build();
    }

    @Test
    @DisplayName("JUnit test for create new Employee method")
    void SaveEmployee() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Ahmed")
                .lastName("Ali")
                .email("ahmed.ali@gmail.com")
                .salary(new BigDecimal(15000))
                .joinDate(LocalDate.now())
                .build();
        Mockito.when(employeeRepo.save(Mockito.any(Employee.class))).thenReturn(employee);
        EmployeeDto savedEmployee = employeeService.createOrUpdate(employeeDto);
        assertNotNull(savedEmployee);
        assertEquals("ahmed.ali@gmail.com", savedEmployee.getEmail());
    }
}

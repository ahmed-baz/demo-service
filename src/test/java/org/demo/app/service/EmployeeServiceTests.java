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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@Log4j2
@SpringBootTest
@ExtendWith(SpringExtension.class)
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
    @DisplayName("JUnit test to create/update employee")
    void testSaveOrUpdateEmployee() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(UUID.randomUUID().toString())
                .firstName("Ahmed")
                .lastName("Ali")
                .email("ahmed.ali@gmail.com")
                .salary(new BigDecimal(15000))
                .joinDate(LocalDate.now())
                .build();
        when(employeeRepo.save(Mockito.any(Employee.class))).thenReturn(employee);
        EmployeeDto savedEmployee = employeeService.createOrUpdate(employeeDto);
        assertNotNull(savedEmployee);
        assertEquals("ahmed.ali@gmail.com", savedEmployee.getEmail());
    }

    @Test
    @DisplayName("JUnit test to delete a employee")
    void testDeleteEmployee() {
        when(employeeRepo.findById(employee.getId())).thenReturn(Optional.of(employee));
        String deleteId = employeeService.delete(employee.getId());
        verify(employeeRepo, times(1)).deleteById(employee.getId());
        assertNotNull(deleteId);
        assertEquals(employee.getId(), deleteId);
    }

    @Test
    @DisplayName("JUnit test to find employee by ID")
    void testFindEmployeeById() {
        when(employeeRepo.findById(Mockito.any(String.class))).thenReturn(Optional.of(employee));
        EmployeeDto employeeDto = employeeService.findById(UUID.randomUUID().toString());
        assertNotNull(employeeDto);
        assertEquals("ahmed.ali@gmail.com", employeeDto.getEmail());
    }

    @Test
    @DisplayName("JUnit test to find all employees")
    void testFindAllEmployees() {
        when(employeeRepo.findAll()).thenReturn(Collections.singletonList(employee));
        List<EmployeeDto> list = employeeService.findList();
        assertNotNull(list);
        assertEquals(1, list.size());
    }

}

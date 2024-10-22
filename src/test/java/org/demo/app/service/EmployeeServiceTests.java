package org.demo.app.service;


import lombok.extern.log4j.Log4j2;
import org.demo.app.dto.EmployeeDto;
import org.demo.app.model.EmployeeEntity;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private EmployeeEntity employeeEntity;

    @BeforeEach
    public void setup() {
        employeeEntity = EmployeeEntity.builder()
                .id(1L)
                .firstName("Ahmed")
                .lastName("Ali")
                .email("ahmed.ali@gmail.com")
                .salary(new BigDecimal(15000))
                .joinDate(new Date())
                .build();
    }

    @Test
    @DisplayName("JUnit test to create/update employee")
    void testSaveOrUpdateEmployee() {
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(1L)
                .firstName("Ahmed")
                .lastName("Ali")
                .email("ahmed.ali@gmail.com")
                .salary(new BigDecimal(15000))
                .joinDate(new Date())
                .build();
        when(employeeRepo.save(Mockito.any(EmployeeEntity.class))).thenReturn(employeeEntity);
        EmployeeDto savedEmployee = employeeService.create(employeeDto);
        assertNotNull(savedEmployee);
        assertEquals("ahmed.ali@gmail.com", savedEmployee.getEmail());
    }

    @Test
    @DisplayName("JUnit test to delete a employee")
    void testDeleteEmployee() {
        when(employeeRepo.findById(employeeEntity.getId())).thenReturn(Optional.of(employeeEntity));
        employeeService.delete(employeeEntity.getId());
        verify(employeeRepo, times(1)).deleteById(employeeEntity.getId());
    }

    @Test
    @DisplayName("JUnit test to find employee by ID")
    void testFindEmployeeById() {
        when(employeeRepo.findById(Mockito.any(Long.class))).thenReturn(Optional.of(employeeEntity));
        EmployeeDto employeeDto = employeeService.findById(1L);
        assertNotNull(employeeDto);
        assertEquals("ahmed.ali@gmail.com", employeeDto.getEmail());
    }

    @Test
    @DisplayName("JUnit test to find all employees")
    void testFindAllEmployees() {
        when(employeeRepo.findAll()).thenReturn(Collections.singletonList(employeeEntity));
        List<EmployeeDto> list = employeeService.findList();
        assertNotNull(list);
        assertEquals(1, list.size());
    }

}

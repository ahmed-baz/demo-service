package org.demo.app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.demo.app.dto.EmployeeDto;
import org.demo.app.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@ExtendWith(SpringExtension.class)
class EmployeeControllerTests {

    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private MockMvc mockMvc;
    private EmployeeDto employeeDto;
    private List<EmployeeDto> employeeDtoList = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        objectMapper.registerModule(new JavaTimeModule());
        EmployeeDto emp1 = EmployeeDto.builder()
                .id(1L)
                .firstName("Ahmed")
                .lastName("Ali")
                .email("ahmed.ali@gmail.com")
                .salary(new BigDecimal(15000))
                .joinDate(new Date())
                .build();
        EmployeeDto emp2 = EmployeeDto.builder()
                .id(2L)
                .firstName("Ahmed")
                .lastName("Hassan")
                .email("ahmed.hassan@gmail.com")
                .salary(new BigDecimal(20000))
                .joinDate(new Date())
                .build();
        employeeDtoList.add(emp1);
        employeeDtoList.add(emp2);
        employeeDto = emp1;
    }

    @Test
    @DisplayName("JUnit test for finding all employees")
    void testFindEmployeeList() throws Exception {
        when(employeeService.findList()).thenReturn(employeeDtoList);
        mockMvc.perform(get("/api/v1/employees"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(employeeDtoList.size())))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("JUnit test for creating dummy employee list")
    void testCreateEmployeeList() throws Exception {
        when(employeeService.createRandomList(employeeDtoList.size())).thenReturn(employeeDtoList);
        mockMvc.perform(get("/api/v1/employees/create/{size}", employeeDtoList.size()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(employeeDtoList.size())))
                .andExpect(jsonPath("$").isArray());
    }


    @Test
    @DisplayName("JUnit test for finding employee by ID")
    void testFindEmployeeById() throws Exception {
        Long id = employeeDto.getId();
        when(employeeService.findById(id)).thenReturn(employeeDto);
        mockMvc.perform(get("/api/v1/employees/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is(employeeDto.getEmail())))
                .andExpect(jsonPath("$.salary", is(15000)))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    @DisplayName("JUnit test for creating new employee")
    void testCreateEmployee() throws Exception {
        EmployeeDto employee = EmployeeDto.builder()
                .firstName("Ahmed")
                .lastName("Ali")
                .email("ahmed.ali@gmail.com")
                .salary(new BigDecimal(20000))
                .build();
        when(employeeService.create(employee)).thenReturn(employee);
        MockHttpServletRequestBuilder httpServletRequestBuilder = post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee));

        mockMvc.perform(httpServletRequestBuilder)
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("JUnit test for updating employee")
    void testUpdateEmployee() throws Exception {
        EmployeeDto employee = EmployeeDto.builder()
                .id(1L)
                .firstName("Ahmed")
                .lastName("Ali")
                .email("ahmed.ali@gmail.com")
                .salary(new BigDecimal(20000))
                .joinDate(new Date())
                .build();
        when(employeeService.update(1L, employee)).thenReturn(employee);
        mockMvc.perform(put("/api/v1/employees/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("JUnit test for deleting employee")
    void testDeleteEmployeeById() throws Exception {
        mockMvc.perform(delete("/api/v1/employees/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}

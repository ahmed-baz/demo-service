package org.demo.app.service;

import org.demo.app.dto.EmployeeDto;

import java.util.List;


public interface EmployeeService {

    List<EmployeeDto> createRandomList(int size);

    List<EmployeeDto> findList();

    EmployeeDto findById(String id);

    EmployeeDto findByEmail(String email);

    Long count();

    EmployeeDto createOrUpdate(EmployeeDto employeeDto);

    String delete(String id);

    void deleteAll();
}

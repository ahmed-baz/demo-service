package org.demo.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.demo.app.dto.EmployeeDto;
import org.demo.app.exception.ResourceNotFoundException;
import org.demo.app.mapper.EmployeeMapper;
import org.demo.app.model.Employee;
import org.demo.app.repo.EmployeeRepo;
import org.demo.app.service.EmployeeService;
import org.demo.app.util.EmployeeUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> createRandomList(int size) {
        List<EmployeeDto> employeeDtoList = EmployeeUtil.getEmployeeDtoList(size);
        List<Employee> employees = employeeMapper.dtoListToEntityList(employeeDtoList);
        employeeRepo.saveAll(employees);
        return employeeMapper.entityListToDtoList(employees);
    }

    @Override
    public List<EmployeeDto> findList() {
        return employeeMapper.entityListToDtoList(employeeRepo.findAll());
    }

    @Override
    public EmployeeDto findById(String id) {
        return employeeMapper.entityToDto(employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public EmployeeDto findByEmail(String email) {
        return employeeMapper.entityToDto(employeeRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(email)));
    }

    @Override
    public Long count() {
        return employeeRepo.count();
    }

    @Override
    public EmployeeDto createOrUpdate(EmployeeDto employeeDto) {
        if (employeeDto.getId() == null) employeeDto.setId(UUID.randomUUID().toString());
        return employeeMapper.entityToDto(employeeRepo.save(employeeMapper.dtoToEntity(employeeDto)));
    }

    @Override
    public String delete(String id) {
        employeeRepo.deleteById(id);
        return id;
    }
}

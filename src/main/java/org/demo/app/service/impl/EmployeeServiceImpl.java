package org.demo.app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.demo.app.dto.EmployeeDto;
import org.demo.app.exception.EmployeeNotFoundException;
import org.demo.app.mapper.EmployeeMapper;
import org.demo.app.model.EmployeeEntity;
import org.demo.app.repo.EmployeeRepo;
import org.demo.app.service.EmployeeService;
import org.demo.app.util.EmployeeUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> createRandomList(int size) {
        List<EmployeeDto> employeeDtoList = EmployeeUtil.getEmployeeDtoList(size);
        List<EmployeeEntity> employeeEntities = employeeMapper.dtoListToEntityList(employeeDtoList);
        employeeRepo.saveAll(employeeEntities);
        return employeeMapper.entityListToDtoList(employeeEntities);
    }

    @Override
    public List<EmployeeDto> findList() {
        return employeeMapper.entityListToDtoList(employeeRepo.findAll());
    }

    @Override
    public EmployeeDto findById(Long id) {
        return employeeMapper.entityToDto(employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id)));
    }

    @Override
    public EmployeeDto findByEmail(String email) {
        return employeeMapper.entityToDto(employeeRepo.findByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(email)));
    }

    @Override
    public Long count() {
        return employeeRepo.count();
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        return employeeMapper.entityToDto(employeeRepo.save(employeeMapper.dtoToEntity(employeeDto)));
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto employeeDto) {
        EmployeeEntity entity = employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeMapper.updateEntity(employeeDto, entity);
        return employeeMapper.entityToDto(employeeRepo.save(entity));
    }

    @Override
    public void delete(Long id) {
        employeeRepo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepo.deleteById(id);
    }

    @Async
    @Override
    public void deleteAll() {
        employeeRepo.deleteAll();
    }
}

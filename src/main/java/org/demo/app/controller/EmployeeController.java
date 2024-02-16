package org.demo.app.controller;


import lombok.RequiredArgsConstructor;
import org.demo.app.dto.EmployeeDto;
import org.demo.app.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> findEmployeeList() {
        return employeeService.findList();
    }

    @GetMapping(value = "/create/{size}")
    public List<EmployeeDto> createRandomList(@PathVariable int size) {
        return employeeService.createRandomList(size);
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable String id) {
        return employeeService.findById(id);
    }

    @GetMapping("/email/{email}")
    public EmployeeDto findByEmail(@PathVariable String email) {
        return employeeService.findByEmail(email);
    }

    @GetMapping("/count")
    public Long count() {
        return employeeService.count();
    }

    @PostMapping
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.createOrUpdate(employeeDto);
    }

    @PutMapping
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.createOrUpdate(employeeDto);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable String id) {
        return employeeService.delete(id);
    }
}

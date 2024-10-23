package org.demo.app.controller;


import lombok.RequiredArgsConstructor;
import org.demo.app.dto.EmployeeDto;
import org.demo.app.payload.AppResponse;
import org.demo.app.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public AppResponse<List<EmployeeDto>> findEmployeeList() {
        List<EmployeeDto> list = employeeService.findList();
        return AppResponse.ok(list);
    }

    @GetMapping(value = "/create/{size}")
    public AppResponse<List<EmployeeDto>> createRandomList(@PathVariable int size) {
        List<EmployeeDto> randomList = employeeService.createRandomList(size);
        return AppResponse.created(randomList);
    }

    @GetMapping("/{id}")
    public AppResponse<EmployeeDto> findById(@PathVariable Long id) {
        return AppResponse.ok(employeeService.findById(id));
    }

    @GetMapping("/query")
    public AppResponse<EmployeeDto> findByEmail(@RequestParam String email) {
        return AppResponse.ok(employeeService.findByEmail(email));
    }

    @GetMapping("/count")
    public AppResponse<Long> count() {
        return AppResponse.ok(employeeService.count());
    }

    @PostMapping
    public AppResponse<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return AppResponse.created(employeeService.create(employeeDto));
    }

    @PutMapping("/{id}")
    public AppResponse<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return AppResponse.ok(employeeService.update(id, employeeDto));
    }

    @DeleteMapping("/{id}")
    public AppResponse<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return AppResponse.noContent();
    }

    @DeleteMapping
    public AppResponse<Void> deleteAll() {
        employeeService.deleteAll();
        return AppResponse.noContent();
    }
}

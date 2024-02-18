package org.demo.app.controller;


import lombok.RequiredArgsConstructor;
import org.demo.app.dto.EmployeeDto;
import org.demo.app.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findEmployeeList() {
        List<EmployeeDto> list = employeeService.findList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/create/{size}")
    public ResponseEntity<List<EmployeeDto>> createRandomList(@PathVariable int size) {
        List<EmployeeDto> randomList = employeeService.createRandomList(size);
        return new ResponseEntity<>(randomList, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable String id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeDto> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(employeeService.findByEmail(email));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(employeeService.count());
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.createOrUpdate(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.createOrUpdate(employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }
}

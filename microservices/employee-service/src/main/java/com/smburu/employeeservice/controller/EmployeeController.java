package com.smburu.employeeservice.controller;

import com.smburu.employeeservice.dto.ApiResponseDto;
import com.smburu.employeeservice.dto.EmployeeDto;
import com.smburu.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto> getEmployee(@PathVariable("id") Long employeeId){
        ApiResponseDto employeeResponse = employeeService.getEmployeeeById(employeeId);
        return new ResponseEntity<>(employeeResponse,HttpStatus.OK);
    }

}

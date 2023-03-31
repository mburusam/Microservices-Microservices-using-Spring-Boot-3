package com.smburu.employeeservice.service;

import com.smburu.employeeservice.dto.ApiResponseDto;
import com.smburu.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    ApiResponseDto getEmployeeeById(Long employeeId);
}

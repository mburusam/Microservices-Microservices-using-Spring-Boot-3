package com.smburu.departmentservice.controller;

import com.smburu.departmentservice.dto.DepartmentDto;
import com.smburu.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<DepartmentDto>saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return  new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentByCode = departmentService.getDepartmentByCode(departmentCode);
       return new ResponseEntity<>(departmentByCode,HttpStatus.OK);
    }

}

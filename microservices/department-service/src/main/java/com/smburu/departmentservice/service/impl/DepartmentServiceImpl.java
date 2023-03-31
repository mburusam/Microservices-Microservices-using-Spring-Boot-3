package com.smburu.departmentservice.service.impl;

import com.smburu.departmentservice.dto.DepartmentDto;
import com.smburu.departmentservice.entity.Department;
import com.smburu.departmentservice.mapper.DepartmentMapper;
import com.smburu.departmentservice.repository.DepartmentRepository;
import com.smburu.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
  /*      Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );*/

        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);
        /*DepartmentDto departmentDto1 = new DepartmentDto(
              savedDepartment.getId(),
              savedDepartment.getDepartmentName(),
              savedDepartment.getDepartmentDescription(),
              savedDepartment.getDepartmentCode()
        );*/
        DepartmentDto departmentDto1 = DepartmentMapper.mapToDepartmentDto(savedDepartment);

        return departmentDto1;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department =departmentRepository.findByDepartmentCode(code);
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }
}

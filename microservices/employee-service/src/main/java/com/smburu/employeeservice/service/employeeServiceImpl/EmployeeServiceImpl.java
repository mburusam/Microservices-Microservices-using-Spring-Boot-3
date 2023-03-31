package com.smburu.employeeservice.service.employeeServiceImpl;

import com.smburu.employeeservice.dto.ApiResponseDto;
import com.smburu.employeeservice.dto.DepartmentDto;
import com.smburu.employeeservice.dto.EmployeeDto;
import com.smburu.employeeservice.entity.Employee;
import com.smburu.employeeservice.repository.EmployeeRepository;
import com.smburu.employeeservice.service.ApiClient;
import com.smburu.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ApiClient apiClient;
   //private final WebClient webClient;
//    private final RestTemplate restTemplate;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto saveEmployeeDto= new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );
        return saveEmployeeDto;
    }

    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod ="getDefaultDepartment")
    @Override
    public ApiResponseDto getEmployeeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();

        //Rest template
     /*   ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8082/api/departments/" + employee.getDepartmentCode(),
                DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();*/

        //Webclient
    /*    DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8082/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
*/

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;
    }

    public ApiResponseDto getDefaultDepartment(Long employeeId,Exception e){

            Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

            EmployeeDto employeeDto = new EmployeeDto(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getDepartmentCode()
            );

            ApiResponseDto apiResponseDto = new ApiResponseDto();
            apiResponseDto.setEmployeeDto(employeeDto);
            apiResponseDto.setDepartmentDto(departmentDto);

            return apiResponseDto;

    }
}

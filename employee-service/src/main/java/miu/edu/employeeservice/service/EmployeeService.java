package miu.edu.employeeservice.service;

import miu.edu.employeeservice.dto.APIResponseDto;
import miu.edu.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}

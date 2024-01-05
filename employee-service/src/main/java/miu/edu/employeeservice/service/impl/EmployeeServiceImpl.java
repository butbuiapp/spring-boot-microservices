package miu.edu.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import miu.edu.employeeservice.dto.APIResponseDto;
import miu.edu.employeeservice.dto.DepartmentDto;
import miu.edu.employeeservice.dto.EmployeeDto;
import miu.edu.employeeservice.entity.Employee;
import miu.edu.employeeservice.mapper.EmployeeMapper;
import miu.edu.employeeservice.repository.EmployeeRepository;
import miu.edu.employeeservice.service.APIClient;
import miu.edu.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;
//    private WebClient webClient;
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee saveDEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(saveDEmployee);

        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        // using RestTemplate
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
//                        "http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                            DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();

        // using WebClient
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block(); // synchronous

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        return apiResponseDto;
    }
}

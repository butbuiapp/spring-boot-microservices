package miu.edu.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import miu.edu.departmentservice.dto.DepartmentDto;
import miu.edu.departmentservice.entity.Department;
import miu.edu.departmentservice.mapper.DepartmentMapper;
import miu.edu.departmentservice.repository.DepartmentRepository;
import miu.edu.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        // convert Department DTO to Department JPA entity
        Department department = modelMapper.map(departmentDto, Department.class);

        Department savedDepartment = departmentRepository.save(department);

        // convert Department JPA entity to Department DTO
        DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);

        return departmentDto;
    }
}

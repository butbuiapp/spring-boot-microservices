package miu.edu.organizationservice.service.impl;

import lombok.AllArgsConstructor;
import miu.edu.organizationservice.dto.OrganizationDto;
import miu.edu.organizationservice.entity.Organization;
import miu.edu.organizationservice.mapper.OrganizationMapper;
import miu.edu.organizationservice.repository.OrganizationRepository;
import miu.edu.organizationservice.service.OrganizationService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        // convert OrganizationDto into Organization jpa entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}

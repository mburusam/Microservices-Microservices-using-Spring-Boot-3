package com.smburu.organizationservice.service.OrganizationServiceImpl;

import com.smburu.organizationservice.dto.OrganizationDto;
import com.smburu.organizationservice.entity.Organization;
import com.smburu.organizationservice.mapper.OrganizationMapper;
import com.smburu.organizationservice.repository.OrganizationRepository;
import com.smburu.organizationservice.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl  implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = OrganizationMapper.maptoEntity(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        OrganizationDto organizationDto1 = OrganizationMapper.maptoDto(savedOrganization);
        return organizationDto1;
    }

    @Override
    public OrganizationDto getOrganizationCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.maptoDto(organization);
    }
}

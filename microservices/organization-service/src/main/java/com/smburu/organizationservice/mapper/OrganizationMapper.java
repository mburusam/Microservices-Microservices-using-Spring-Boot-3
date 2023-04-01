package com.smburu.organizationservice.mapper;

import com.smburu.organizationservice.dto.OrganizationDto;
import com.smburu.organizationservice.entity.Organization;

public class OrganizationMapper {
    public static OrganizationDto maptoDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getOrganizationCreatedDate()
        );
        return organizationDto;
    }

    public static Organization maptoEntity(OrganizationDto organizationDto){
        Organization organization = new Organization(
                organizationDto.getId(),
                organizationDto.getOrganizationName(),
                organizationDto.getOrganizationDescription(),
                organizationDto.getOrganizationCode(),
                organizationDto.getOrganizationCreatedDate()
        );
        return organization;
    }
}

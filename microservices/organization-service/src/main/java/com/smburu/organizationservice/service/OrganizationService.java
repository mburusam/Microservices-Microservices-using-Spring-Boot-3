package com.smburu.organizationservice.service;

import com.smburu.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationCode(String organizationCode);
}

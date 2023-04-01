package com.smburu.organizationservice.controller;

import com.smburu.organizationservice.dto.OrganizationDto;
import com.smburu.organizationservice.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto organizationDto1 = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(organizationDto1, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto>getOrganization(@PathVariable("code") String organizationCode){
        OrganizationDto organizationDto = organizationService.getOrganizationCode(organizationCode);
        return new ResponseEntity<>(organizationDto,HttpStatus.OK);
    }


}

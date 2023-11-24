package com.organization.vehicleManager.dtoMapper;

import com.organization.vehicleManager.dto.RegionRequest;
import com.organization.vehicleManager.dto.RegionResponse;
import com.organization.vehicleManager.entity.Region;
import com.organization.vehicleManager.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegionDtoMapper {

    public static Region convertToEntity(RegionRequest regionRequest, CompanyService companyService) {
            Region region = new Region();
            region.setRegionName(regionRequest.getRegionName());
            region.setRegionCode(regionRequest.getRegionCode());
            region.setCompany(companyService.getEntity(regionRequest.getCompanyId()));
            log.info("RegionDtoMapper::Converting RegionRequest to Region entity completed");
            return region;

    }

    public static RegionResponse convertToResponse(Region region) {
            RegionResponse regionResponse = new RegionResponse();
            regionResponse.setId(region.getId());
            regionResponse.setRegionName(region.getRegionName());
            regionResponse.setRegionCode(region.getRegionCode());
            regionResponse.setCompanyId(region.getCompany().getId());
            log.info("RegionDtoMapper::Converting Region entity to RegionResponse completed");
            return regionResponse;
    }
}
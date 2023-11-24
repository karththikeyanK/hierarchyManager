package com.organization.vehicleManager.service;

import com.organization.vehicleManager.dto.RegionRequest;
import com.organization.vehicleManager.dto.RegionResponse;
import com.organization.vehicleManager.dtoMapper.RegionDtoMapper;
import com.organization.vehicleManager.entity.Region;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.repo.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private CompanyService companyService;

    public RegionResponse createRegion(RegionRequest regionRequest) {
        try {
            log.info("RegionService::Create Region started");
            if (regionRepository.existsByRegionName(regionRequest.getRegionName())) {
                log.error("RegionService::Validation failed: Region name already exists");
                throw new GeneralBusinessException("Region name already exists");
            }
            if (regionRepository.existsByRegionCode(regionRequest.getRegionCode())) {
                log.error("RegionService::Validation failed: Region code already exists");
                throw new GeneralBusinessException("Region code already exists");
            }
            Region region = RegionDtoMapper.convertToEntity(regionRequest,companyService);
            region = regionRepository.save(region);
            log.info("RegionService::Create Region completed");
            return RegionDtoMapper.convertToResponse(region);
        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("RegionService::Error while creating Region: {}", e.getMessage());
            throw e;
        }
    }


    public RegionResponse updateRegion(Integer id,RegionRequest regionRequest) {
        try {
            log.info("RegionService::Update Region started");
            Region region = regionRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Region not found with id: " + id + " to update"));
            if (regionRepository.existsByRegionName(regionRequest.getRegionName()) && !region.getRegionName().equals(regionRequest.getRegionName())) {
                log.error("RegionService::Validation failed: Region name already exists");
                throw new GeneralBusinessException("Region name already exists");
            }
            if (regionRepository.existsByRegionCode(regionRequest.getRegionCode()) && !region.getRegionCode().equals(regionRequest.getRegionCode())) {
                log.error("RegionService::Validation failed: Region code already exists");
                throw new GeneralBusinessException("Region code already exists");
            }
            region = RegionDtoMapper.convertToEntity(regionRequest,companyService);
            region.setId(id);
            region = regionRepository.save(region);
            log.info("RegionService::Update Region completed");
            return RegionDtoMapper.convertToResponse(region);

        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("RegionService::Error while updating Region: {}", e.getMessage());
            throw e;
        }
    }


    public List<RegionResponse> getAllRegions() {
        try {
            log.info("RegionService::Get All Regions started");
            List<Region> regions = regionRepository.findAll();
            if (regions.isEmpty()) {
                log.error("RegionService::No Regions found");
                throw new GeneralBusinessException("No Regions found");
            }
            log.info("RegionService::Get All Regions completed");
            List<RegionResponse> regionResponses = regions.stream().map(RegionDtoMapper::convertToResponse).toList();
            if (regionResponses.isEmpty()) {
                log.error("RegionService::Error while converting to List<RegionResponse> regionResponses");
                throw new GeneralBusinessException("Error while converting to List<RegionResponse> regionResponses");
            }
            return regionResponses;
        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("RegionService::Error while getting All Regions: {}", e.getMessage());
            throw e;
        }
    }



    public RegionResponse getRegion(Integer id) {
        try {
            log.info("RegionService::Get Region started");
            Region region = regionRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Region not found with id: " + id));
            log.info("RegionService::Get Region completed");
            return RegionDtoMapper.convertToResponse(region);
        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("RegionService::Error while getting Region: {}", e.getMessage());
            throw e;
        }
    }

    public String deleteRegion(Integer id) {
        try {
            log.info("RegionService::Delete Region started");
            Region region = regionRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Region not found with id: " + id + " to delete"));
            regionRepository.delete(region);
            log.info("RegionService::Delete Region completed");
            return "Region deleted successfully";
        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("RegionService::Error while deleting Region: {}", e.getMessage());
            throw e;
        }
    }



    public Region getRegionEntity(Integer id) {
        try {
            log.info("RegionService::Get Region started");
            Region region = regionRepository.findById(id).orElseThrow(() -> new GeneralBusinessException("Region not found with id: " + id));
            log.info("RegionService::Get Region completed");
            return region;
        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("RegionService::Error while getting Region: {}", e.getMessage());
            throw e;
        }
    }


}

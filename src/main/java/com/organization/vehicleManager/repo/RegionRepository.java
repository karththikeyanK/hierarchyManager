package com.organization.vehicleManager.repo;

import com.organization.vehicleManager.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    boolean existsByRegionName(String regionName);

    boolean existsByRegionCode(String regionCode);
}

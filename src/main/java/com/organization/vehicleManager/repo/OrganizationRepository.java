package com.organization.vehicleManager.repo;

import com.organization.vehicleManager.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    boolean existsByName(String name);

    boolean existsByRegNo(String regNo);
}

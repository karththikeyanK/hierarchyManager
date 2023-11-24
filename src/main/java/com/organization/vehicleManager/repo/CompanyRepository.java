package com.organization.vehicleManager.repo;

import com.organization.vehicleManager.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByName(String name);

    boolean existsByRegNo(String regNo);

    List<Company> findByOrganizationId(Integer organizationId);
}

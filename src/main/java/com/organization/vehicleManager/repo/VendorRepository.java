package com.organization.vehicleManager.repo;

import com.organization.vehicleManager.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    boolean existsByRegNo(String regNo);

    boolean existsByContact(String contact);
}

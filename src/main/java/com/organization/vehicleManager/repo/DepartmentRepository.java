package com.organization.vehicleManager.repo;

import com.organization.vehicleManager.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByCode(String code);

    boolean existsByName(String name);
}

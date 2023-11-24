package com.organization.vehicleManager.repo;

import com.organization.vehicleManager.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByContactNumber(String contactNo);

    Customer findByContactNumber(String contactNumber);

    List<Customer> findByCompanyId(Integer id);
}

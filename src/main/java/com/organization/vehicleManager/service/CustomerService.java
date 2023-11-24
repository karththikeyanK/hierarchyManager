package com.organization.vehicleManager.service;

import com.organization.vehicleManager.dto.CustomerRequest;
import com.organization.vehicleManager.dto.CustomerResponse;
import com.organization.vehicleManager.dtoMapper.CustomerDtoMapper;
import com.organization.vehicleManager.entity.Customer;
import com.organization.vehicleManager.exception.GeneralBusinessException;
import com.organization.vehicleManager.repo.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CompanyService companyService;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        log.info("CustomerService::Creating customer started");
        try {
            requestValidation(customerRequest);
            if(customerRepository.existsByContactNumber(customerRequest.getContactNo())){
                throw new GeneralBusinessException("Customer contact number already exists");
            }
            Customer customer = CustomerDtoMapper.convertToEntity(customerRequest, companyService);
            customer = customerRepository.save(customer);
            log.info("CustomerService::Creating customer completed");
            return CustomerDtoMapper.convertToResponse(customer);

        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("CustomerService::Error while creating customer: {}", e.getMessage());
            throw e;
        }
    }

    public CustomerResponse updateCustomer(Integer customerId, CustomerRequest customerRequest) {
        log.info("CustomerService::Updating customer started");
        try {
            requestValidation(customerRequest);
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new GeneralBusinessException("Customer not found with id: " + customerId));
            if(customerRepository.existsByContactNumber(customerRequest.getContactNo()) && !customer.getContactNumber().equals(customerRequest.getContactNo())){
                throw new GeneralBusinessException("Customer contact number already exists");
            }
            customer = CustomerDtoMapper.convertToEntity(customerRequest, companyService);
            customer.setId(customerId);
            customer = customerRepository.save(customer);
            log.info("CustomerService::Updating customer completed");
            return CustomerDtoMapper.convertToResponse(customer);

        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("CustomerService::Error while updating customer: {}", e.getMessage());
            throw e;
        }
    }


    public CustomerResponse getCustomer(Integer customerId) {
        log.info("CustomerService::Getting customer started");
        try {
            if (customerId == null) {
                log.error("CustomerService::getCustomer()::Customer id is null");
                throw new GeneralBusinessException("Customer id is null");
            }
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new GeneralBusinessException("Customer not found with id: " + customerId));
            log.info("CustomerService::Getting customer completed");
            return CustomerDtoMapper.convertToResponse(customer);
        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("CustomerService::Error while getting customer: {}", e.getMessage());
            throw e;
        }
    }

    public List<CustomerResponse> getAllCustomers() {
        log.info("CustomerService::Getting all customers started");
        try {
            List<Customer> customers = customerRepository.findAll();
            if (customers.isEmpty()) {
                log.error("CustomerService::Customers not found");
                throw new GeneralBusinessException("Customers not found database returned empty");
            }
            log.info("CustomerService::Getting all customers completed");
            List<CustomerResponse> customerResponses = customers.stream().map(CustomerDtoMapper::convertToResponse).toList();
            if (customerResponses.isEmpty()) {
                log.error("CustomerService::Customers not found");
                throw new GeneralBusinessException("Customers Response List is empty");
            }
            return customerResponses;
        }catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("CustomerService::Error while getting all customers: {}", e.getMessage());
            throw e;
        }

    }

    public String deleteCustomer(Integer customerId) {
        log.info("CustomerService::Deleting customer started");
        try {
            if (customerId == null) {
                log.error("CustomerService::deleteCustomer::Customer id is null");
                throw new GeneralBusinessException("Customer id is null");
            }
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new GeneralBusinessException("Customer not found with id: " + customerId));
            customerRepository.delete(customer);
            log.info("CustomerService::Deleting customer completed");
            return "Customer deleted successfully";
        } catch (Exception e) {
            log.error("CustomerService::Error while deleting customer: {}", e.getMessage());
            throw e;
        }
    }

    public Customer getCustomerEntity(Integer customerId) {
        log.info("CustomerService::Getting customer entity started");
        try {
            if (customerId == null) {
                log.error("CustomerService::getCustomerEntity()::Customer id is null");
                throw new GeneralBusinessException("Customer id is null");
            }
            Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new GeneralBusinessException("Customer not found with id: " + customerId));
            log.info("CustomerService::Getting customer entity completed");
            return customer;
        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("CustomerService::Error while getting customer entity: {}", e.getMessage());
            throw e;
        }

    }

    public CustomerResponse getCustomerEntityByContactNumber(String contactNumber) {
        log.info("CustomerService::Getting customer entity started");
        try {
            Customer customer = customerRepository.findByContactNumber(contactNumber);
            log.info("CustomerService::Getting customer entity completed");
            if (customer == null || customer.getId() == null){
                return null;
            }
            return CustomerDtoMapper.convertToResponse(customer);
        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("CustomerService::Error while getting customer entity: {}", e.getMessage());
            throw e;
        }

    }

    private void requestValidation(CustomerRequest customerRequest) {
        log.info("CustomerService::Validating customer request started");
        try {
            if (customerRequest.getName() == null || customerRequest.getName().isEmpty()) {
                throw new GeneralBusinessException("Customer name is required");
            }
            if (customerRequest.getAddress() == null || customerRequest.getAddress().isEmpty()) {
                throw new GeneralBusinessException("Customer address is required");
            }
            if (customerRequest.getContactNo() == null || customerRequest.getContactNo().isEmpty()) {
                throw new GeneralBusinessException("Customer contact number is required");
            }
            if (customerRequest.getCompanyIds() == null || customerRequest.getCompanyIds().isEmpty()) {
                throw new GeneralBusinessException("Company Id is required");
            }
            log.info("CustomerService::Validating customer request completed");
        } catch (GeneralBusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("CustomerService::Error while validating customer request: {}", e.getMessage());
            throw e;
        }
    }


    public List<CustomerResponse> getCustomerByCompanyId(Integer id) {
        try{
            log.info("CustomerService::Getting customer by company id started");
            List<Customer> customerList = customerRepository.findByCompanyId(id);
            if (customerList.isEmpty()){
                log.error("CustomerService::Customer not found");
                throw new GeneralBusinessException("Customer not found");
            }
            log.info("CustomerService::Getting customer by company id completed");
            List<CustomerResponse> customerResponseList = new ArrayList<>();
            customerList.forEach(customer -> {
                CustomerResponse customerResponse = CustomerDtoMapper.convertToResponse(customer);
                log.info("CustomerService::Getting customer {} by company id completed-->{}",customerResponse.getName(), customerResponse.getCompanyNames());
                customerResponseList.add(customerResponse);
            });
            return  customerResponseList;
        }catch (GeneralBusinessException e){
            throw e;
        }catch (Exception e){
            log.error("CustomerService::Error while getting customer by company id: {}", e.getMessage());
            throw e;
        }
    }
}
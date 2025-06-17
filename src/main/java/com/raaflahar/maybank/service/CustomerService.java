package com.raaflahar.maybank.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.raaflahar.maybank.dto.request.CustomerRequest;
import com.raaflahar.maybank.dto.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest request);
    CustomerResponse getCustomerById(UUID id);
    Page<CustomerResponse> getAllCustomers(Pageable pageable);
}

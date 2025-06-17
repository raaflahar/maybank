package com.raaflahar.maybank.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.raaflahar.maybank.dto.request.CustomerRequest;
import com.raaflahar.maybank.dto.response.CustomerResponse;
import com.raaflahar.maybank.entity.Customer;
import com.raaflahar.maybank.exception.CustomerNotFoundException;
import com.raaflahar.maybank.mapper.CustomerMapper;
import com.raaflahar.maybank.repository.CustomerRepository;
import com.raaflahar.maybank.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        customer.setCreatedAt(LocalDateTime.now());
        customer = customerRepository.save(customer);
        return customerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customerMapper.toResponse(customer);
    }

    @Override
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        Page<Customer> page = customerRepository.findAll(pageable);
        return page.map(customerMapper::toResponse);
    }
}

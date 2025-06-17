package com.raaflahar.maybank.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raaflahar.maybank.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}

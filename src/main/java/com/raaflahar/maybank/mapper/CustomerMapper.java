package com.raaflahar.maybank.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.raaflahar.maybank.dto.request.CustomerRequest;
import com.raaflahar.maybank.dto.response.CustomerResponse;
import com.raaflahar.maybank.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toEntity(CustomerRequest request);

    CustomerResponse toResponse(Customer entity);
}

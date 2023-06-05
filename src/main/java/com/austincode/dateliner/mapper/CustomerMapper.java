/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.CustomerDto;
import com.austincode.dateliner.model.Customer;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Yvan Ngakeu
 */

@Mapper(componentModel = "spring", imports = UUID.class)
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);
    
    CustomerDto toCustomerDto(Customer customer);
    
    List<CustomerDto> modelsToDtos(List<Customer> customers);
    
    @Mapping(target = "customer_id", expression = "java(UUID.randomUUID())")
    Customer toCustomer(CustomerDto customerDto);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.OrderDto;
import com.austincode.dateliner.model.Orders;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Yvan Ngakeu
 */
@Mapper(componentModel = "spring", imports = {UUID.class, Date.class})
public interface OrderMapper {
    
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);
    
   OrderDto toOrderDto(Orders order);
   
   List<OrderDto> modelsToDtos(List<Orders> orders);
   
   @Mapping(target = "order_id", expression = "java(UUID.randomUUID())")
   @Mapping(target = "order_date", expression = "java(new Date())")
   Orders toOrder(OrderDto orderDto);
    
}

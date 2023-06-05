/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.PaymentDto;
import com.austincode.dateliner.model.Payment;
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
public interface PaymentMapper {
    
    PaymentMapper MAPPER = Mappers.getMapper(PaymentMapper.class);
    
   PaymentDto toPaymentDto(Payment payment);
   
   List<PaymentDto> modelsToDtos(List<Payment> payments);
   
   @Mapping(target = "payment_id", expression = "java(UUID.randomUUID())")
   Payment toPayment(PaymentDto paymentDto);
    
}

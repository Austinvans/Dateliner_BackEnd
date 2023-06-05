/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.PaymentMethodsDto;
import com.austincode.dateliner.model.PaymentMethods;
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
public interface PaymentMethodsMapper {
    
    PaymentMethodsMapper MAPPER = Mappers.getMapper(PaymentMethodsMapper.class);
    
    PaymentMethodsDto toPaymentMethodsDto(PaymentMethods paymentMethods);
    
    List<PaymentMethodsDto> modelsToDtos(List<PaymentMethods> paymentMethods);
    
    @Mapping(target = "payment_mode_id", expression = "java(UUID.randomUUID())")
    PaymentMethods toPaymentMethods(PaymentMethodsDto paymentMethodsDto);
    
}

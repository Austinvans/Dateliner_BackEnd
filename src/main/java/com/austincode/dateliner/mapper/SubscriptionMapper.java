/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.SubscriptionDto;
import com.austincode.dateliner.model.Subscriptions;
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
public interface SubscriptionMapper {
    SubscriptionMapper MAPPER = Mappers.getMapper(SubscriptionMapper.class);
    
    SubscriptionDto  toSubscriptionDto(Subscriptions subscription);
    
    List<SubscriptionDto> modelsToDtos(List<Subscriptions> subscriptions);
    //List<SellerDto> modelToDto(List<Seller> seller);
    
    @Mapping(target = "subscription_id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "subscription_start_date", defaultExpression = "java(new Date())")
    @Mapping(target = "subscription_end_date", defaultExpression = "java(new Date())")
    Subscriptions toSubscription(SubscriptionDto subscriptionDto);
    
}

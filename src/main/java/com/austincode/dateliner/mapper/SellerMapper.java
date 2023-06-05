/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.SellerDto;
import com.austincode.dateliner.model.Seller;
import java.util.List;
import java.util.UUID;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Yvan Ngakeu
 */

@Mapper(componentModel = "spring", imports = UUID.class)
public interface SellerMapper {
    
    SellerMapper MAPPER = Mappers.getMapper(SellerMapper.class);
    
    @InheritInverseConfiguration
    SellerDto toSellerDto(Seller seller);
    
    List<SellerDto> modelsToDtos(List<Seller> sellers);
    //List<SellerDto> modelToDto(List<Seller> seller);
    
    @Mapping(target = "seller_id", expression = "java(UUID.randomUUID())")
    Seller toSeller(SellerDto sellerDto);
    
}

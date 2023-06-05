/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.BillDto;
import com.austincode.dateliner.model.Bill;
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
public interface BillMapper {
    BillMapper MAPPER = Mappers.getMapper(BillMapper.class);
    
    BillDto toBillDto(Bill bill);
    
    List<BillDto> modelsToDtos(List<Bill> Bills);
    
    @Mapping(target = "bill_id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "bill_date", expression = "java(new Date())")
    Bill toBill(BillDto billDto);
}

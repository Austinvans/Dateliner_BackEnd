/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.TimelinerBySellerDto;
import com.austincode.dateliner.model.TimelinerBySeller;
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
public interface TimelinerBySellerMapper {
    
    TimelinerBySellerMapper MAPPER = Mappers.getMapper(TimelinerBySellerMapper.class);
    
    TimelinerBySellerDto toTimelinerBySellerDto(TimelinerBySeller timelinerBySeller);
    
    List<TimelinerBySellerDto> modelsToDtos(List<TimelinerBySeller> timelinerBySeller);
    
    @Mapping(target = "timeliner_start_date", defaultExpression = "java(new Date())")
    @Mapping(target = "timeliner_end_date", defaultExpression = "java(new Date())")
    @Mapping(target = "timeliner_status", defaultValue = "OnGoing")
    TimelinerBySeller toTimelinerBySeller(TimelinerBySellerDto timelinerBySellerDto);
    
}

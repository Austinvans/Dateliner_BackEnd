/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.TimelinerByCustomerDto;
import com.austincode.dateliner.model.TimelinerByCustomer;
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
public interface TimelinerByCustomerMapper {
    
    TimelinerByCustomerMapper MAPPER = Mappers.getMapper(TimelinerByCustomerMapper.class);
    
    TimelinerByCustomerDto toTimelinerByCustomerDto(TimelinerByCustomer timelinerByCustomer);
        
    List<TimelinerByCustomerDto> modelsToDtos(List<TimelinerByCustomer> timelinerByCustomer);
    
    @Mapping(target = "timeliner_start_date", defaultExpression = "java(new Date())")
    @Mapping(target = "timeliner_end_date", defaultExpression = "java(new Date())")
    @Mapping(target = "timeliner_status", defaultValue = "OnGoing")
    TimelinerByCustomer toTimelinerByCustomer(TimelinerByCustomerDto timelinerByCustomerDto);
}

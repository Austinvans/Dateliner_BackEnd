/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.DatelinerByTimelinerDto;
import com.austincode.dateliner.model.DatelinerByTimeliner;
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
public interface DatelinerByTimelinerMapper {
 
    DatelinerByTimelinerMapper MAPPER = Mappers.getMapper(DatelinerByTimelinerMapper.class);
    
    DatelinerByTimelinerDto toDatelinerByTimelinerDto(DatelinerByTimeliner datelinerByTimeliner);
    
    List<DatelinerByTimelinerDto> modelsToDtos(List<DatelinerByTimeliner> datelinerByTimeliner);
    
    @Mapping(target = "timeliner_start_date", defaultExpression = "java(new Date())")
    @Mapping(target = "dateliner_id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "dateliner_start_date", defaultExpression = "java(new Date())")
    @Mapping(target = "timeliner_end_date", defaultExpression = "java(new Date())")
    @Mapping(target = "dateliner_end_date", defaultExpression = "java(new Date())")
    @Mapping(target = "timeliner_status", defaultValue = "OnGoing")
    @Mapping(target = "dateliner_status", defaultValue = "OnGoing")
    @Mapping(target = "reminder_mean_one", defaultValue = "Sms")
    @Mapping(target = "reminder_mean_two", defaultValue = "Email")
    @Mapping(target = "reminder_mean_three", defaultValue = "Whatsapp")
    DatelinerByTimeliner toDatelinerByTimeliner(DatelinerByTimelinerDto datelinerByTimelinerDto);
}

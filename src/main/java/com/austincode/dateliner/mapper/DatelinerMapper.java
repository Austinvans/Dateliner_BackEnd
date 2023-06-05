/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.DatelinerDto;
import com.austincode.dateliner.model.Dateliner;
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
public interface DatelinerMapper {
    
    DatelinerMapper MAPPER = Mappers.getMapper(DatelinerMapper.class);

    DatelinerDto toDatelinerDto(Dateliner dateliner);
    
    List<DatelinerDto> modelsToDtos(List<Dateliner> dateliner);
    
    @Mapping(target = "dateliner_id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "dateliner_start_date", defaultExpression = "java(new Date())")
    @Mapping(target = "dateliner_end_date", defaultExpression = "java(new Date())")
    @Mapping(target = "dateliner_status", defaultValue = "OnGoing")
    Dateliner toDateliner(DatelinerDto datelinerDto);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.ObjectsByTimelinerDto;
import com.austincode.dateliner.model.ObjectsByTimeliner;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Yvan Ngakeu
 */
@Mapper(componentModel = "spring", imports = {UUID.class})
public interface ObjectsByTimelinerMapper {
    
    ObjectsByTimelinerMapper MAPPER = Mappers.getMapper(ObjectsByTimelinerMapper.class);
    
    ObjectsByTimelinerDto toObjectsByTimelinerDto(ObjectsByTimeliner objectsByTimeliner);
    
    List<ObjectsByTimelinerDto> modelsToDtos(List<ObjectsByTimeliner> objectsByTimeliner);
    
    @Mapping(target = "guarantee_id", expression = "java(UUID.randomUUID())")
    ObjectsByTimeliner toObjectsByTimeliner(ObjectsByTimelinerDto objectsByTimelinerDto);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.ReminderMeansDto;
import com.austincode.dateliner.model.ReminderMeans;
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
public interface ReminderMeansMapper {
    ReminderMeansMapper MAPPER = Mappers.getMapper(ReminderMeansMapper.class);
    
    ReminderMeansDto toReminderMeansDto(ReminderMeans reminderMeans);
    
    List<ReminderMeansDto> modelsToDtos(List<ReminderMeans> reminderMeans);
    
    @Mapping(target = "reminder_mean_id", expression = "java(UUID.randomUUID())")
    ReminderMeans toReminderMeans(ReminderMeansDto reminderMeansDto);
}

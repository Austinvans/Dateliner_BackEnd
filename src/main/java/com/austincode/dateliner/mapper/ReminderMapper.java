/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.ReminderDto;
import com.austincode.dateliner.model.Reminder;
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
public interface ReminderMapper {
    
    ReminderMapper MAPPER = Mappers.getMapper(ReminderMapper.class);
    
    ReminderDto toReminderDto(Reminder reminder);
    
    List<ReminderDto> modelsToDtos(List<Reminder> Reminders);
    
    @Mapping(target = "reminder_id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "reminder_date", expression = "java(new Date())")
    Reminder toReminder(ReminderDto reminderDto);
    
}

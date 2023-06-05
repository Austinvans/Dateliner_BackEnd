/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.ReminderDto;
import com.austincode.dateliner.mapper.ReminderMapper;
import com.austincode.dateliner.model.Reminder;
import com.austincode.dateliner.service.ReminderService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yvan Ngakeu
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/dateliner")
public class ReminderController {
    
    @Autowired
    ReminderService reminderService;
    
    @Autowired
    ReminderMapper reminderMapper;
    
    // L'AJOUT D'UNE RELANCE 
    @PostMapping("/Reminders")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ReminderDto reminderDto) {
        Reminder reminder = reminderMapper.toReminder(reminderDto);
        reminderService.save(reminder);
    }
    
    // LISTE DES RELANCES
    @GetMapping("/Reminders")
    @ResponseStatus(HttpStatus.OK)
    public List<ReminderDto> findAll() {
        return reminderMapper.modelsToDtos(reminderService.findAll());
    }
    
    // RECHERCHER UNE RELANCE PAR SON ID
    @GetMapping("/Reminders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReminderDto findOne(@PathVariable("id") UUID id) {
        return reminderMapper.toReminderDto(reminderService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UNE RELANCE
    @PutMapping("/Reminders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody ReminderDto reminderDto){
        Reminder reminder = reminderMapper.toReminder(reminderDto);
        reminder.setReminder_id(id);
        reminderService.save(reminder);
    }
    
    // SUPPRIMMER UNE FACTURE PAR SON ID 
    @DeleteMapping("/Reminders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        reminderService.deleteById(id);       
    }
    
    @DeleteMapping("/Reminders")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllReminders() {
        reminderService.deleteAll();      
    }
    
}

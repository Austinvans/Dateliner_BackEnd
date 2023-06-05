/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.ReminderMeansDto;
import com.austincode.dateliner.mapper.ReminderMeansMapper;
import com.austincode.dateliner.model.ReminderMeans;
import com.austincode.dateliner.service.ReminderMeansService;
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
public class ReminderMeansController {
    
    @Autowired
    ReminderMeansService reminderMeansService;
    
    @Autowired
    ReminderMeansMapper reminderMeansMapper;
    
    // L'AJOUT D'UN MOYEN DE RELANCE 
    @PostMapping("/remindermeans")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ReminderMeansDto reminderMeansDto) {
        ReminderMeans reminderMeans = reminderMeansMapper.toReminderMeans(reminderMeansDto);
        reminderMeansService.save(reminderMeans);
    }
    
    // LISTE DES MOYENS DE RELANCE
    @GetMapping("/remindermeans")
    @ResponseStatus(HttpStatus.OK)
    public List<ReminderMeansDto> findAll() {
        return reminderMeansMapper.modelsToDtos(reminderMeansService.findAll());
    }
    
    // RECHERCHER UN MOYEN DE RELANCE PAR SON ID
    @GetMapping("/remindermeans/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReminderMeansDto findOne(@PathVariable("id") UUID id) {
        return reminderMeansMapper.toReminderMeansDto(reminderMeansService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UN MOYEN DE RELANCE 
    @PutMapping("/ReminderMeans/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody ReminderMeansDto reminderMeansDto){
        ReminderMeans reminderMeans = reminderMeansMapper.toReminderMeans(reminderMeansDto);
        reminderMeans.setReminder_mean_id(id);
        reminderMeansService.save(reminderMeans);
    }
    
    // SUPPRIMMER UN MOYEN DE RELANCE PAR SON ID 
    @DeleteMapping("/remindermeans/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        reminderMeansService.deleteById(id);       
    }
    
    @DeleteMapping("/remindermeans")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllReminderMeans() {
        reminderMeansService.deleteAll();      
    }
}

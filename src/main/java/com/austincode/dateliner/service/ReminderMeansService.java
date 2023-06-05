/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.ReminderMeans;
import com.austincode.dateliner.repository.ReminderMeansRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class ReminderMeansService {
    
    @Autowired
    ReminderMeansRepository reminderMeansRepository;
    
    public ReminderMeans save(ReminderMeans reminderMeans){
        System.out.println("Saving new ReminderMeans");
        return reminderMeansRepository.save(reminderMeans);
    }
    public List<ReminderMeans> findAll() {
        System.out.println("Fetching all ReminderMeans");
        return reminderMeansRepository.findAll();
    }
    
    public ReminderMeans findOne(UUID id) {
        System.out.println("Searching ReminderMeans id {} "+ id);
        return reminderMeansRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting ReminderMeans id {} "+ id);
        reminderMeansRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting ReminderMeans id {} ");
        reminderMeansRepository.deleteAll();
    }
}

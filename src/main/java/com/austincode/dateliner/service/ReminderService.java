/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.Reminder;
import com.austincode.dateliner.repository.ReminderRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class ReminderService {
    
    @Autowired
    ReminderRepository reminderRepository;
    
    public Reminder save(Reminder reminder){
        System.out.println("Saving new Reminder");
        return reminderRepository.save(reminder);
    }
    public List<Reminder> findAll() {
        System.out.println("Fetching all Reminders");
        return reminderRepository.findAll();
    }

    public Reminder findOne(UUID id) {
        System.out.println("Searching Reminder id {} "+ id);
        return reminderRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting Reminder id {} "+ id);
        reminderRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting Reminders");
        reminderRepository.deleteAll();
    }
}

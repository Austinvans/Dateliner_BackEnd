/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.Timeliner;
import com.austincode.dateliner.repository.TimelinerRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class TimelinerService {
    
    @Autowired
    TimelinerRepository timelinerRepository;
    
    public Timeliner save(Timeliner timeliner){
        System.out.println("Saving new Timeliner");
        return timelinerRepository.save(timeliner);
    }
    public List<Timeliner> findAll() {
        System.out.println("Fetching all Timeliners");
        return timelinerRepository.findAll();
    }

    public Timeliner findOne(UUID id) {
        System.out.println("Searching Timeliner id {} "+ id);
        return timelinerRepository.findById(id).orElse(null);
    }
    
    public List<Timeliner> findByName(String name) {
        System.out.println("Fetching all Customers with name "+name);
        return timelinerRepository.findByName(name);
    }
    
    public List<Timeliner> findByStatus(String name) {
        System.out.println("Fetching all Timeliners with status "+name);
        return timelinerRepository.findByStatus(name);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting Timeliner id {} "+ id);
        timelinerRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting Timeliners");
        timelinerRepository.deleteAll();
    }
    
}

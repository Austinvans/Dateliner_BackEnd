/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.ObjectsByTimeliner;
import com.austincode.dateliner.repository.ObjectsByTimelinerRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class ObjectsByTimelinerService {
    
    @Autowired
    ObjectsByTimelinerRepository ObjectsByTimelinerRepository;
    
    public ObjectsByTimeliner save(ObjectsByTimeliner objectsByTimeliner){
        System.out.println("Saving new ObjectByTimeliner");
        return ObjectsByTimelinerRepository.save(objectsByTimeliner);
    }
    public List<ObjectsByTimeliner> findAll() {
        System.out.println("Fetching all ObjectsByTimeliner");
        return ObjectsByTimelinerRepository.findAll();
    }

    public ObjectsByTimeliner findOne(UUID id1, UUID id2) {
        System.out.println("Searching ObjectByTimeliner id {} " +id1+ " and " + id2);
        return ObjectsByTimelinerRepository.findById(BasicMapId.id("timeliner_id", id1).with("guarantee_id", id2)).orElse(null);
    }
    
    public ObjectsByTimeliner findTimeliner(UUID id) {
        System.out.println("Searching ObjectsByTimeliner id {} " + id);
        return ObjectsByTimelinerRepository.findById(BasicMapId.id("timeliner_id", id)).orElse(null);
    }
    
    public List<ObjectsByTimeliner> findOneTimeliner(UUID id) {
        System.out.println("Searching DatelinerByTimeliner id {} " + id);
        return ObjectsByTimelinerRepository.findByTimeliner(id);
    }
    
    public List<ObjectsByTimeliner> findByName(String name) {
        System.out.println("Fetching all Customers with name "+name);
        return ObjectsByTimelinerRepository.findByName(name);
    }
    
    public void deleteById(UUID id1, UUID id2){
        System.out.println("Deleting ObjectByTimeliner id {} "+id1+ " and " + id2);
        ObjectsByTimelinerRepository.deleteById(BasicMapId.id("timeliner_id", id1).with("dateliner_id", id2));
    }
    
    public void deleteByTimliner(UUID id){
        System.out.println("Deleting ObjectByTimeliner id {} "+ id);
        ObjectsByTimelinerRepository.deleteById(BasicMapId.id("timeliner_id", id));
    }
    
    public void deleteAll(){
        System.out.println("Deleting ObjectsByTimeliner");
        ObjectsByTimelinerRepository.deleteAll();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.DatelinerByTimeliner;
import com.austincode.dateliner.repository.DatelinerByTimelinerRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class DatelinerByTimelinerService {
    
    @Autowired
    DatelinerByTimelinerRepository datelinerByTimelinerRepository;
    
    public DatelinerByTimeliner save(DatelinerByTimeliner datelinerByTimeliner){
        System.out.println("Saving new DatelinerByTimeliner");
        return datelinerByTimelinerRepository.save(datelinerByTimeliner);
    }
    public List<DatelinerByTimeliner> findAll() {
        System.out.println("Fetching all DatelinerByTimeliner");
        return datelinerByTimelinerRepository.findAll();
        //return datelinerByTimelinerRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public DatelinerByTimeliner findOne(UUID id1, UUID id2) {
        System.out.println("Searching DatelinerByTimeliner id {} " +id1+ " and " + id2);
        return datelinerByTimelinerRepository.findById(BasicMapId.id("timeliner", id1).with("dateliner_id", id2)).orElse(null);
    }
    
    public List<DatelinerByTimeliner> findOneTimeliner(UUID id) {
        System.out.println("Searching DatelinerByTimeliner id {} " + id);
        return datelinerByTimelinerRepository.findByTimeliner(id);
    }
    
    public List<DatelinerByTimeliner> findByName(String name) {
        System.out.println("Fetching all Customers with name "+name);
        return datelinerByTimelinerRepository.findByName(name);
    }
    
    public void deleteById(UUID id1, UUID id2){
        System.out.println("Deleting DatelinerByTimeliner id {} "+id1+ " and " + id2);
        datelinerByTimelinerRepository.deleteById(BasicMapId.id("timeliner", id1).with("dateliner_id", id2));
    }
    
    public void deleteByTimliner(UUID id){
        System.out.println("Deleting DatelinerByTimeliner id {} "+ id);
        datelinerByTimelinerRepository.deleteById(BasicMapId.id("timeliner", id));
    }
    
    public void deleteAll(){
        System.out.println("Deleting DatelinerByTimeliners");
        datelinerByTimelinerRepository.deleteAll();
    }
    
}

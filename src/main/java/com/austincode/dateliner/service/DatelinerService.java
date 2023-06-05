/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.Dateliner;
import com.austincode.dateliner.repository.DatelinerRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class DatelinerService {
    
    @Autowired
    DatelinerRepository datelinerRepository;
    
    public Dateliner save(Dateliner dateliner){
        System.out.println("Saving new Dateliner");
        return datelinerRepository.save(dateliner);
    }
    public List<Dateliner> findAll() {
        System.out.println("Fetching all Dateliners");
        return datelinerRepository.findAll();
    }

    public Dateliner findOne(UUID id) {
        System.out.println("Searching Dateliner id {} "+ id);
        return datelinerRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting Dateliner id {} "+ id);
        datelinerRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting Dateliners");
        datelinerRepository.deleteAll();
    }
    
}

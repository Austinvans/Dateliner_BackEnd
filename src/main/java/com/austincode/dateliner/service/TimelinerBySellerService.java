/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.TimelinerBySeller;
import com.austincode.dateliner.repository.TimelinerBySellerRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class TimelinerBySellerService {
    
    @Autowired
    TimelinerBySellerRepository timelinerBySellerRepository;
    
    public TimelinerBySeller save(TimelinerBySeller timelinerBySeller){
        System.out.println("Saving new TimelinerBySeller");
        return timelinerBySellerRepository.save(timelinerBySeller);
    }
    public List<TimelinerBySeller> findAll() {
        System.out.println("Fetching all TimelinerBySeller");
        return timelinerBySellerRepository.findAll();
    }

    public TimelinerBySeller findOne(UUID id) {
        System.out.println("Searching TimelinerBySeller id {} "+ id);
        return timelinerBySellerRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting TimelinerBySeller id {} "+ id);
        timelinerBySellerRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting TimelinerBySellers");
        timelinerBySellerRepository.deleteAll();
    }
    
    
    
}

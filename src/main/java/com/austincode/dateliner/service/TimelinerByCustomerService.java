/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.TimelinerByCustomer;
import com.austincode.dateliner.repository.TimelinerByCustomerRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class TimelinerByCustomerService {
    
    @Autowired
    TimelinerByCustomerRepository timelinerByCustomerRepository;
    
    public TimelinerByCustomer save(TimelinerByCustomer timelinerByCustomer){
        System.out.println("Saving new TimelinerByCustomer");
        return timelinerByCustomerRepository.save(timelinerByCustomer);
    }
    public List<TimelinerByCustomer> findAll() {
        System.out.println("Fetching all TimelinerByCustomer");
        return timelinerByCustomerRepository.findAll();
    }

    public TimelinerByCustomer findOne(UUID id) {
        System.out.println("Searching TimelinerByCustomer id {} "+ id);
        return timelinerByCustomerRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting TimelinerByCustomer id {} "+ id);
        timelinerByCustomerRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting TimelinerByCustomers");
        timelinerByCustomerRepository.deleteAll();
    }
    
}

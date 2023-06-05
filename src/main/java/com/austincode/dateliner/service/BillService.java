/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.Bill;
import com.austincode.dateliner.repository.BillRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */

@Service
public class BillService {
    @Autowired
    BillRepository billRepository;
    
    public Bill save(Bill bill){
        System.out.println("Saving new Bill");
        return billRepository.save(bill);
    }
    public List<Bill> findAll() {
        System.out.println("Fetching all Bills");
        return billRepository.findAll();
    }

    public Bill findOne(UUID id) {
        System.out.println("Searching Bill id {} "+ id);
        return billRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting Bill id {} "+ id);
        billRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting Bill");
        billRepository.deleteAll();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.Payment;
import com.austincode.dateliner.repository.PaymentRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */

@Service
public class PaymentService {
    
    @Autowired
    PaymentRepository paymentRepository;
    
    public Payment save(Payment payment){
        System.out.println("Saving new Payment");
        return paymentRepository.save(payment);
    }
    public List<Payment> findAll() {
        System.out.println("Fetching all Payments");
        return paymentRepository.findAll();
    }

    public Payment findOne(UUID id) {
        System.out.println("Searching Payment id {} "+ id);
        return paymentRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting Payment id {} "+ id);
        paymentRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting Payments");
        paymentRepository.deleteAll();
    }
}

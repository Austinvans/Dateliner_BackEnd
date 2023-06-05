/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.PaymentMethods;
import com.austincode.dateliner.repository.PaymentMethodsRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class PaymentMethodsService {
    
    @Autowired
    PaymentMethodsRepository paymentMethodsRepository;
    
    public PaymentMethods save(PaymentMethods paymentMethods){
        System.out.println("Saving new PaymentMethod");
        return paymentMethodsRepository.save(paymentMethods);
    }
    public List<PaymentMethods> findAll() {
        System.out.println("Fetching all PaymentMethods");
        return paymentMethodsRepository.findAll();
    }
    
    public PaymentMethods findOne(UUID id) {
        System.out.println("Searching PaymentMethod id {} "+ id);
        return paymentMethodsRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting PaymentMethod id {} "+ id);
        paymentMethodsRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting PaymentMethods id {} ");
        paymentMethodsRepository.deleteAll();
    }
}

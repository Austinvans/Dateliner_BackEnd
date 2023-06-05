/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.Subscriptions;
import com.austincode.dateliner.repository.SubscriptionRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    
    public Subscriptions save(Subscriptions subscription){
        System.out.println("Saving new Subscription");
        return subscriptionRepository.save(subscription);
    }
    public List<Subscriptions> findAll() {
        System.out.println("Fetching all Subscriptions");
        return subscriptionRepository.findAll();
    }
    
    public Subscriptions findOne(UUID id) {
        System.out.println("Searching Subscription id {} "+ id);
        return subscriptionRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting Subscription id {} "+ id);
        subscriptionRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting Subscriptions ");
        subscriptionRepository.deleteAll();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.SubscriptionDto;
import com.austincode.dateliner.mapper.SubscriptionMapper;
import com.austincode.dateliner.model.Subscriptions;
import com.austincode.dateliner.service.SubscriptionService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Yvan Ngakeu
 */
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/dateliner")
public class SubscriptionController {
    
    @Autowired
    SubscriptionService subscriptionService;
    
    @Autowired
    SubscriptionMapper subscriptionMapper;
    
    // L'AJOUT D'UN ABONNEMENT 
    @PostMapping("/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody SubscriptionDto subscriptionDto) {
        Subscriptions subscription = subscriptionMapper.toSubscription(subscriptionDto);
        subscriptionService.save(subscription);
    }
    
    // LISTE DES ABONNEMENTS
    @GetMapping("/Subscriptions")
    @ResponseStatus(HttpStatus.OK)
    public List<SubscriptionDto> findAll() {
        return subscriptionMapper.modelsToDtos(subscriptionService.findAll());
    }
    
    // RECHERCHER UN ABONNEMENT PAR SON ID
    @GetMapping("/Subscriptions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubscriptionDto findOne(@PathVariable("id") UUID id) {
        return subscriptionMapper.toSubscriptionDto(subscriptionService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UN ABONNEMENT
    @PutMapping("/Subscriptions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody SubscriptionDto subscriptionDto){
        Subscriptions subscription = subscriptionMapper.toSubscription(subscriptionDto);
        subscription.setSubscription_id(id);
        subscriptionService.save(subscription);
    }
    
    // SUPPRIMMER UN ECHEANCIER PAR SON ID 
    @DeleteMapping("/Subscriptions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        subscriptionService.deleteById(id);       
    }
    
    @DeleteMapping("/Subscriptions")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllSubscriptions() {
        subscriptionService.deleteAll();      
    }
}

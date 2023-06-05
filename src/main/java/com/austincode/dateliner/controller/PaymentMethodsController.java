/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.PaymentMethodsDto;
import com.austincode.dateliner.mapper.PaymentMethodsMapper;
import com.austincode.dateliner.model.PaymentMethods;
import com.austincode.dateliner.service.PaymentMethodsService;
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
public class PaymentMethodsController {
    
    @Autowired
    PaymentMethodsService paymentMethodsService;
    
    @Autowired
    PaymentMethodsMapper paymentMethodsMapper;
    
    // L'AJOUT D'UN MOYEN DE PAIEMENT
    @PostMapping("/paymentmethods")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PaymentMethodsDto paymentMethodsDto) {
        PaymentMethods paymentMethods = paymentMethodsMapper.toPaymentMethods(paymentMethodsDto);
        paymentMethodsService.save(paymentMethods);
    }
    
    // LISTE DES MOYENS DE RELANCE
    @GetMapping("/paymentmethods")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentMethodsDto> findAll() {
        return paymentMethodsMapper.modelsToDtos(paymentMethodsService.findAll());
    }
    
    // RECHERCHER UN MOYEN DE PAIEMENT PAR SON ID
    @GetMapping("/paymentmethods/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentMethodsDto findOne(@PathVariable("id") UUID id) {
        return paymentMethodsMapper.toPaymentMethodsDto(paymentMethodsService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UN PAIEMENT DE RELANCE 
    @PutMapping("/paymentmethods/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody PaymentMethodsDto paymentMethodsDto){
        PaymentMethods paymentMethods = paymentMethodsMapper.toPaymentMethods(paymentMethodsDto);
        paymentMethods.setPayment_mode_id(id);
        paymentMethodsService.save(paymentMethods);
    }
    
    // SUPPRIMMER UN MOYEN DE PAIEMENT PAR SON ID 
    @DeleteMapping("/paymentmethods/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        paymentMethodsService.deleteById(id);       
    }
    
    @DeleteMapping("/paymentmethods")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllPaymentMethods() {
        paymentMethodsService.deleteAll();      
    }
}

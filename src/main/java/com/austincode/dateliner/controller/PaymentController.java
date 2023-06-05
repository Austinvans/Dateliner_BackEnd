/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.PaymentDto;
import com.austincode.dateliner.mapper.PaymentMapper;
import com.austincode.dateliner.model.Payment;
import com.austincode.dateliner.service.PaymentService;
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
public class PaymentController {
    
    @Autowired
    PaymentService paymentService;
    
    @Autowired
    PaymentMapper paymentMapper;
    
     // L'AJOUT D'UNE FACTURE 
    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PaymentDto paymentDto) {
        Payment payment = paymentMapper.toPayment(paymentDto);
        paymentService.save(payment);
    }
    
    // LISTE DES FACTURES
    @GetMapping("/payments")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDto> findAll() {
        return paymentMapper.modelsToDtos(paymentService.findAll());
    }
    
    // RECHERCHER UNE FACTURE PAR SON ID
    @GetMapping("/payments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDto findOne(@PathVariable("id") UUID id) {
        return paymentMapper.toPaymentDto(paymentService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UNE FACTURE
    @PutMapping("/payments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody PaymentDto paymentDto ){
        Payment payment = paymentMapper.toPayment(paymentDto);
        payment.setPayment_id(id);
        paymentService.save(payment);
    }
    
    // SUPPRIMMER UNE FACTURE PAR SON ID 
    @DeleteMapping("/payments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        paymentService.deleteById(id);       
    }
    
    @DeleteMapping("/payments")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllPayments() {
        paymentService.deleteAll();      
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.BillDto;
import com.austincode.dateliner.mapper.BillMapper;
import com.austincode.dateliner.model.Bill;
import com.austincode.dateliner.service.BillService;
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
public class BillController {
    
    @Autowired
    BillService billService;
    
    @Autowired
    BillMapper billMapper;
    
    // L'AJOUT D'UNE FACTURE 
    @PostMapping("/bills")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody BillDto billDto) {
        Bill bill = billMapper.toBill(billDto);
        billService.save(bill);
    }
    
    // LISTE DES FACTURES
    @GetMapping("/bills")
    @ResponseStatus(HttpStatus.OK)
    public List<BillDto> findAll() {
        return billMapper.modelsToDtos(billService.findAll());
    }
    
    // RECHERCHER UNE FACTURE PAR SON ID
    @GetMapping("/bills/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BillDto findOne(@PathVariable("id") UUID id) {
        return billMapper.toBillDto(billService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UNE FACTURE
    @PutMapping("/bills/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody BillDto billDto){
        Bill bill = billMapper.toBill(billDto);
        bill.setBill_id(id);
        billService.save(bill);
    }
    
    // SUPPRIMMER UNE FACTURE PAR SON ID 
    @DeleteMapping("/bills/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        billService.deleteById(id);       
    }
    
    @DeleteMapping("/bills")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllBills() {
        billService.deleteAll();      
    }
}

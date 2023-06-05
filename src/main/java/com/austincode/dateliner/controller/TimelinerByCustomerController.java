/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.TimelinerByCustomerDto;
import com.austincode.dateliner.mapper.TimelinerByCustomerMapper;
import com.austincode.dateliner.model.TimelinerByCustomer;
import com.austincode.dateliner.service.TimelinerByCustomerService;
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
public class TimelinerByCustomerController {
    
    @Autowired
    TimelinerByCustomerService timelinerByCustomerService;
    @Autowired
    TimelinerByCustomerMapper timelinerByCustomerMapper; 
    
    // L'AJOUT D'UN ECHEANCIER 
    @PostMapping("/timelinerByCustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody TimelinerByCustomerDto timelinerByCustomerDto) {
        TimelinerByCustomer timelinerByCustomer = timelinerByCustomerMapper.toTimelinerByCustomer(timelinerByCustomerDto);
        timelinerByCustomerService.save(timelinerByCustomer);
    }
    
    // LISTE DES ECHEANCIERS
    @GetMapping("/timelinerByCustomer")
    @ResponseStatus(HttpStatus.OK)
    public List<TimelinerByCustomerDto> findAll() {
        return timelinerByCustomerMapper.modelsToDtos(timelinerByCustomerService.findAll());
    }
    
    // RECHERCHER UN ECHEANCIER PAR SON ID
    @GetMapping("/timelinerByCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TimelinerByCustomerDto findOne(@PathVariable("id") UUID id) {
        return timelinerByCustomerMapper.toTimelinerByCustomerDto(timelinerByCustomerService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UN ECHEANCIER
    @PutMapping("/timelinerByCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody TimelinerByCustomerDto timelinerBySellerDto){
        TimelinerByCustomer timelinerByCustomer = timelinerByCustomerMapper.toTimelinerByCustomer(timelinerBySellerDto);
        timelinerByCustomer.setTimeliner_id(id);
        timelinerByCustomerService.save(timelinerByCustomer);
    }
    
    // SUPPRIMMER UN ECHEANCIER PAR SON ID 
    @DeleteMapping("/timelinerByCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        timelinerByCustomerService.deleteById(id);       
    }
    
    @DeleteMapping("/timelinerByCustomer")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllTimeliners() {
        timelinerByCustomerService.deleteAll();      
    }
    
    
}

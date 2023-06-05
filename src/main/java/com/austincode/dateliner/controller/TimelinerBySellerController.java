/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.TimelinerBySellerDto;
import com.austincode.dateliner.mapper.TimelinerBySellerMapper;
import com.austincode.dateliner.model.TimelinerBySeller;
import com.austincode.dateliner.service.TimelinerBySellerService;
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
public class TimelinerBySellerController {
    
    @Autowired
    TimelinerBySellerService timelinerBySellerService;
    @Autowired
    TimelinerBySellerMapper timelinerBySellerMapper; 
    
    // L'AJOUT D'UN ECHEANCIER 
    @PostMapping("/timelinerBySeller")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody TimelinerBySellerDto timelinerBySellerDto) {
        TimelinerBySeller timelinerBySeller = timelinerBySellerMapper.toTimelinerBySeller(timelinerBySellerDto);
        timelinerBySellerService.save(timelinerBySeller);
    }
    
    // LISTE DES ECHEANCIERS
    @GetMapping("/timelinerBySeller")
    @ResponseStatus(HttpStatus.OK)
    public List<TimelinerBySellerDto> findAll() {
        return timelinerBySellerMapper.modelsToDtos(timelinerBySellerService.findAll());
    }
    
    // RECHERCHER UN ECHEANCIER PAR SON ID
    @GetMapping("/timelinerBySeller/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TimelinerBySellerDto findOne(@PathVariable("id") UUID id) {
        return timelinerBySellerMapper.toTimelinerBySellerDto(timelinerBySellerService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UN ECHEANCIER
    @PutMapping("/timelinerBySeller/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody TimelinerBySellerDto timelinerBySellerDto){
        TimelinerBySeller timelinerBySeller = timelinerBySellerMapper.toTimelinerBySeller(timelinerBySellerDto);
        timelinerBySeller.setTimeliner_id(id);
        timelinerBySellerService.save(timelinerBySeller);
    }
    
    // SUPPRIMMER UN ECHEANCIER PAR SON ID 
    @DeleteMapping("/timelinerBySeller/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        timelinerBySellerService.deleteById(id);       
    }
    
    @DeleteMapping("/timelinerBySeller")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllTimeliners() {
        timelinerBySellerService.deleteAll();      
    }
   
}

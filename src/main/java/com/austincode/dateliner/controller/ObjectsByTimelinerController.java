/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.ObjectsByTimelinerDto;
import com.austincode.dateliner.mapper.ObjectsByTimelinerMapper;
import com.austincode.dateliner.model.ObjectsByTimeliner;
import com.austincode.dateliner.service.ObjectsByTimelinerService;
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
public class ObjectsByTimelinerController {
    
    @Autowired
    ObjectsByTimelinerService objectsByTimelinerService;
    
    @Autowired
    ObjectsByTimelinerMapper objectsByTimelinerMapper; 
    
    // L'AJOUT D'UN ECHEANCIER 
    @PostMapping("/objectsByTimeliner")
    @ResponseStatus(HttpStatus.CREATED)
    public ObjectsByTimelinerDto save(@RequestBody ObjectsByTimelinerDto objectsByTimelinerDto) {
        ObjectsByTimeliner objectsByTimeliner = objectsByTimelinerMapper.toObjectsByTimeliner(objectsByTimelinerDto);
        objectsByTimelinerService.save(objectsByTimeliner);
        return objectsByTimelinerMapper.toObjectsByTimelinerDto(objectsByTimeliner);
    }
    
    // LISTE DES ECHEANCIERS
    @GetMapping("/objectsByTimeliners")
    @ResponseStatus(HttpStatus.OK)
    public List<ObjectsByTimelinerDto> findAll() {
        return objectsByTimelinerMapper.modelsToDtos(objectsByTimelinerService.findAll());
    }
    
    //RECHERCHER UN ECHEANCIER PAR SON ID
    @GetMapping("/objectsByTimeliner/{id1}/{id2}")
    @ResponseStatus(HttpStatus.OK)
    public ObjectsByTimelinerDto findOne(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2) {
        return objectsByTimelinerMapper.toObjectsByTimelinerDto(objectsByTimelinerService.findOne(id1, id2));
    }
    
    // LISTE DES CLIENTS
    @GetMapping("/objectsByTimeliner")
    @ResponseStatus(HttpStatus.OK)
    public List<ObjectsByTimelinerDto> findCustomers(String name) {
        return objectsByTimelinerMapper.modelsToDtos(objectsByTimelinerService.findByName(name));
    }
    
    @PutMapping("/objectsByTimeliner/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody ObjectsByTimelinerDto objectsByTimelinerDto){
        ObjectsByTimeliner objectsByTimeliner = objectsByTimelinerMapper.toObjectsByTimeliner(objectsByTimelinerDto);
        objectsByTimeliner.setTimeliner(id);
        objectsByTimelinerService.save(objectsByTimeliner);
    }
    
    // SUPPRIMMER UN ECHEANCIER PAR SON ID 
    @DeleteMapping("/objectsByTimeliner/{id1}/{id2}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id1") UUID id1, @PathVariable("id2") UUID id2) {
        objectsByTimelinerService.deleteById(id1, id2);       
    }
    
    // SUPPRIMMER UN ECHEANCIER PAR SON ID 
    @DeleteMapping("/objectsByTimeliner/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTimeliner(@PathVariable("id") UUID id) {
        objectsByTimelinerService.deleteByTimliner(id);       
    }
    
    @DeleteMapping("/objectsByTimeliner")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllTimeliners() {
        objectsByTimelinerService.deleteAll();      
    }
    
}

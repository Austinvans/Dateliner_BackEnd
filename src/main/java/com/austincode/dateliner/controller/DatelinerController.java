/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.DatelinerDto;
import com.austincode.dateliner.mapper.DatelinerMapper;
import com.austincode.dateliner.model.Dateliner;
import com.austincode.dateliner.service.DatelinerService;
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
public class DatelinerController {
    
    @Autowired
    DatelinerService datelinerService;
    
    @Autowired
    DatelinerMapper datelinerMapper;
    
    // L'AJOUT D'UNE ECHEANCE 
    @PostMapping("/dateliners")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody DatelinerDto datelinerDto) {
        Dateliner timeliner = datelinerMapper.toDateliner(datelinerDto);
        datelinerService.save(timeliner);
    }
    
    // LISTE DES ECHEANCES
    @GetMapping("/dateliners")
    @ResponseStatus(HttpStatus.OK)
    public List<DatelinerDto> findAll() {
        return datelinerMapper.modelsToDtos(datelinerService.findAll());
    }
    
    // RECHERCHER UNE ECHEANCE PAR SON ID
    @GetMapping("/dateliners/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DatelinerDto findOne(@PathVariable("id") UUID id) {
        return datelinerMapper.toDatelinerDto(datelinerService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UN ECHEANCIER
    @PutMapping("/dateliners/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody DatelinerDto datelinerDto){
        Dateliner dateliner = datelinerMapper.toDateliner(datelinerDto);
        dateliner.setDateliner_id(id);
        datelinerService.save(dateliner);
    }
    
    // SUPPRIMMER UN ECHEANCIER PAR SON ID 
    @DeleteMapping("/dateliners/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        datelinerService.deleteById(id);       
    }
    
    @DeleteMapping("/dateliners")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllDateliners() {
        datelinerService.deleteAll();      
    }
}

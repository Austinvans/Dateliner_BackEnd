/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.TimelinerDto;
import com.austincode.dateliner.mapper.TimelinerMapper;
import com.austincode.dateliner.model.Timeliner;
import com.austincode.dateliner.service.TimelinerService;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
public class TimelinerController {
    
    @Autowired
    TimelinerService timelinerService;
    @Autowired
    TimelinerMapper timelinerMapper; 
    
    public Date current_date;
    public Date end_date;
    
    // L'AJOUT D'UN ECHEANCIER 
    @PostMapping("/timeliner")
    @ResponseStatus(HttpStatus.CREATED)
    public TimelinerDto save(@RequestBody TimelinerDto timelinerDto) {
        Timeliner timeliner = timelinerMapper.toTimeliner(timelinerDto);
        timelinerService.save(timeliner);
        return timelinerMapper.toTimelinerDto(timeliner);
    }
    
    // LISTE DES ECHEANCIERS
    @GetMapping("/timeliners")
    @ResponseStatus(HttpStatus.OK)
    public List<TimelinerDto> findAll() {
        return timelinerMapper.modelsToDtos(timelinerService.findAll());
    }
    
    //RECHERCHER UN ECHEANCIER PAR SON ID
    @GetMapping("/timeliner/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TimelinerDto findOne(@PathVariable("id") UUID id) {
        return timelinerMapper.toTimelinerDto(timelinerService.findOne(id));
    }
    
    // LISTE DES CLIENTS
    @GetMapping("/timeliner/customer")
    @ResponseStatus(HttpStatus.OK)
    public List<TimelinerDto> findCustomers(String name) {
        return timelinerMapper.modelsToDtos(timelinerService.findByName(name));
    }
    
    // LISTE DES CLIENTS
    @GetMapping("/timeliner/status/expired")
    @ResponseStatus(HttpStatus.OK)
    public List<TimelinerDto> findExpiredStatus() {
        return timelinerMapper.modelsToDtos(timelinerService.findByStatus("Expired"));
    }
    
    // MODIFIER LES INFOS D'UN ECHEANCIER
    @PutMapping("/timeliner/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody TimelinerDto timelinerDto){
        Timeliner timeliner = timelinerMapper.toTimeliner(timelinerDto);
        timeliner.setTimeliner_id(id);
        timelinerService.save(timeliner);
    }
    
    @Scheduled(fixedRate=50000)
    public void checkStatus(){
        current_date = new Date();
        List<TimelinerDto> timeliners;
        timeliners = timelinerMapper.modelsToDtos(timelinerService.findAll());
        for(int i=0; i<timeliners.size(); i++){
            end_date = timeliners.get(i).getTimeliner_end_date();
            String status = timeliners.get(i).getStatus();
            if(end_date.before(current_date)){
                if("OnGoing".equals(status)){
                    updatestatus(timeliners.get(i).getTimeliner_id());
                    System.out.println("Status updated on timeliner : " + timeliners.get(i).getTimeliner_id());
                }
                else{
                    System.out.println("No updates found on timeliner");
                }
            }
        }
    }
    // MODIFIER LES INFOS D'UN ECHEANCIER
    @PatchMapping("/timeliner/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatestatus(@PathVariable("id") UUID id){
        Timeliner timeliner = timelinerService.findOne(id);
        timeliner.setTimeliner_id(id);
        timeliner.setStatus("Expired");
        timelinerService.save(timeliner);
    }
    
    // MODIFIER LES INFOS D'UN ECHEANCIER
    @PatchMapping("/timeliner/done/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatestatusdone(@PathVariable("id") UUID id){
        Timeliner timeliner = timelinerService.findOne(id);
        timeliner.setTimeliner_id(id);
        timeliner.setStatus("Done");
        timelinerService.save(timeliner);
    }
    
    
    
    // SUPPRIMMER UN ECHEANCIER PAR SON ID 
    @DeleteMapping("/timeliner/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        timelinerService.deleteById(id);       
    }
    
    
    @DeleteMapping("/timeliner")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllTimeliners() {
        timelinerService.deleteAll();      
    }   
}
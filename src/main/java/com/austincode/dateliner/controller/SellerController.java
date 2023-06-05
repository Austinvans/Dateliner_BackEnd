/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.SellerDto;
import com.austincode.dateliner.mapper.SellerMapper;
import com.austincode.dateliner.model.Seller;
import com.austincode.dateliner.service.SellerService;
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
public class SellerController {
    
    @Autowired
    SellerService sellerService;
    
    @Autowired
    SellerMapper sellerMapper;
    
    // L'AJOUT D'UN VENDEUR 
    @PostMapping("/sellers")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody SellerDto sellerDto) {
        Seller seller = sellerMapper.toSeller(sellerDto);
        sellerService.save(seller);
    }
    
    // LISTE DES VENDEURS
    @GetMapping("/sellers")
    @ResponseStatus(HttpStatus.OK)
    public List<SellerDto> findAll() {
        return sellerMapper.modelsToDtos(sellerService.findAll());
    }
    
    // RECHERCHER UN VENDEUR PAR SON ID
    @GetMapping("/sellers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SellerDto findOne(@PathVariable("id") UUID id) {
        return sellerMapper.toSellerDto(sellerService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UN VENDEUR
    @PutMapping("/sellers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") UUID id, @RequestBody SellerDto sellerDto){
        Seller seller = sellerMapper.toSeller(sellerDto);
        seller.setSeller_id(id);
        sellerService.save(seller);
    }
    
    // SUPPRIMMER UN VENDEUR PAR SON ID 
    @DeleteMapping("/sellers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        sellerService.deleteById(id);       
    }
    
    @DeleteMapping("/sellers")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllSellers() {
        sellerService.deleteAll();      
    }
    
}

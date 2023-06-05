/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.CurrencyDto;
import com.austincode.dateliner.mapper.CurrencyMapper;
import com.austincode.dateliner.model.Currency;
import com.austincode.dateliner.service.CurrencyService;
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
public class CurrencyController {
 
    @Autowired
    CurrencyService currencyService;
    
    @Autowired
    CurrencyMapper currencyMapper;
    
    // L'AJOUT D'UNE MONNAIE 
    @PostMapping("/currencies")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCurrency(@RequestBody CurrencyDto currencyDto) {
        Currency currency = currencyMapper.toCurrency(currencyDto);
        currencyService.save(currency);
    }
    
    // LISTE DES MONNAIES
    @GetMapping("/currencies")
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyDto> findAllCurrencies() {
        return currencyMapper.modelsToDtos(currencyService.findAll());
    }
    
    // RECHERCHER UNE MONNAIE PAR SON ID
    @GetMapping("/currencies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyDto findOneCurrency(@PathVariable("id") UUID id) {
        return currencyMapper.toCurrencyDto(currencyService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UNE MONNAIE 
    @PutMapping("/currencies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCurrency(@PathVariable("id") UUID id, @RequestBody CurrencyDto currencyDto){
        Currency currency = currencyMapper.toCurrency(currencyDto);
        currency.setCurrency_id(id);
        currencyService.save(currency);
    }
    
    // SUPPRIMMER UNE MONNAIE PAR SON ID 
    @DeleteMapping("/currencies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCurrency(@PathVariable("id") UUID id) {
        currencyService.deleteById(id);       
    }
    
    @DeleteMapping("/currencies")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllCurrencies() {
        currencyService.deleteAll();      
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.Currency;
import com.austincode.dateliner.repository.CurrencyRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class CurrencyService {
    
    @Autowired
    CurrencyRepository currencyRepository;
    
    public Currency save(Currency currency){
        System.out.println("Saving new Currency");
        return currencyRepository.save(currency);
    }
    public List<Currency> findAll() {
        System.out.println("Fetching all Currencies");
        return currencyRepository.findAll();
    }
    
    public Currency findOne(UUID id) {
        System.out.println("Searching Currency id {} "+ id);
        return currencyRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting Currency id {} "+ id);
        currencyRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting Currencies ");
        currencyRepository.deleteAll();
    }
}

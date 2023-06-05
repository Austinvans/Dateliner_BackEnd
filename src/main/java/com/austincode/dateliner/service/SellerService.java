/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.Seller;
import com.austincode.dateliner.repository.SellerRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */


@Service
public class SellerService {
    
    @Autowired
    SellerRepository sellerRepository;
    
    public Seller save(Seller seller){
        System.out.println("Saving new Seller");
        return sellerRepository.save(seller);
    }
    public List<Seller> findAll() {
        System.out.println("Fetching all Sellers");
        return sellerRepository.findAll();
    }

    public Seller findOne(UUID id) {
        System.out.println("Searching Seller id {} "+ id);
        return sellerRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting Seller id {} "+ id);
        sellerRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting Seller");
        sellerRepository.deleteAll();
    }
    
}

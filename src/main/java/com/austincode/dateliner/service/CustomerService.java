/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.Customer;
import com.austincode.dateliner.repository.CustomerRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository customerRepository;
    
    public Customer save(Customer customer){
        System.out.println("Saving new Customer");
        return customerRepository.save(customer);
    }
    public List<Customer> findAll() {
        System.out.println("Fetching all Customers");
        return customerRepository.findAll();
    }
    public Customer findByName(String name) {
        //System.out.println("Fetching all Customers with name "+name);
        return customerRepository.findByName(name);
    }
    
    public Customer findOne(UUID id) {
        System.out.println("Searching Customer id {} "+ id);
        return customerRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting Customer id {} "+ id);
        customerRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting Customer id {} ");
        customerRepository.deleteAll();
    }
}

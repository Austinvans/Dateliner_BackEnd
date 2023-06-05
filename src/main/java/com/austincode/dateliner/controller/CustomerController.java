/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.CustomerDto;
import com.austincode.dateliner.mapper.CustomerMapper;
import com.austincode.dateliner.model.Customer;
import com.austincode.dateliner.service.CustomerService;
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
public class CustomerController {
    
    @Autowired
    CustomerService customerService;
    
    @Autowired
    CustomerMapper customerMapper;
    
    // L'AJOUT D'UN CLIENT 
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerMapper.toCustomer(customerDto);
        customerService.save(customer);
    }
    
    // LISTE DES CLIENTS
    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> findAllCustomers() {
        return customerMapper.modelsToDtos(customerService.findAll());
    }
    
    // LISTE DES CLIENTS
    @GetMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto findCustomers(String name) {
        return customerMapper.toCustomerDto(customerService.findByName(name));
    }
    
    // RECHERCHER UN CLIENT PAR SON ID
    @GetMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto findOneCustomer(@PathVariable("id") UUID id) {
        return customerMapper.toCustomerDto(customerService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UN CLIENT
    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable("id") UUID id, @RequestBody CustomerDto customerDto){
        CustomerDto currentinfo = customerMapper.toCustomerDto(customerService.findOne(id));
        if(customerDto.getName()!=""){
            currentinfo.setName(customerDto.getName());
        }
        if(customerDto.getCustomer_surname()!=""){
            currentinfo.setName(customerDto.getCustomer_surname());
        }
        if(customerDto.getCustomer_email()!=""){
            currentinfo.setName(customerDto.getCustomer_email());
        }
        if(customerDto.getCustomer_tel()!=""){
            currentinfo.setName(customerDto.getCustomer_tel());
        }
        Customer customer = customerMapper.toCustomer(currentinfo);
        customer.setCustomer_id(id);
        customerService.save(customer);
        return customerMapper.toCustomerDto(customer);
    }
    
    // SUPPRIMMER UN CLIENT PAR SON ID 
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("id") UUID id) {
        customerService.deleteById(id);       
    }
    
    // SUPPRIMMER TOUT LES CLIENTS
    @DeleteMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllCustomers() {
        customerService.deleteAll();      
    }
    
}

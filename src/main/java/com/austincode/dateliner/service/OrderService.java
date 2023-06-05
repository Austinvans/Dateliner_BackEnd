/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.Orders;
import com.austincode.dateliner.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepository;
    
    public Orders save(Orders order){
        System.out.println("Saving new Order");
        return orderRepository.save(order);
    }
    public List<Orders> findAll() {
        System.out.println("Fetching all Orders");
        return orderRepository.findAll();
    }

    public Orders findOne(UUID id) {
        System.out.println("Searching Order id {} "+ id);
        return orderRepository.findById(id).orElse(null);
    }
    
    public void deleteById(UUID id){
        System.out.println("Deleting Order id {} "+ id);
        orderRepository.deleteById(id);
    }
    
    public void deleteAll(){
        System.out.println("Deleting Orders");
        orderRepository.deleteAll();
    }
    
}

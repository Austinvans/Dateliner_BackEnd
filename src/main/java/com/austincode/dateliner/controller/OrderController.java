/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.OrderDto;
import com.austincode.dateliner.mapper.OrderMapper;
import com.austincode.dateliner.model.Orders;
import com.austincode.dateliner.service.OrderService;
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



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/dateliner")
public class OrderController {
    
    @Autowired
    OrderService orderService;
    
    @Autowired
    OrderMapper orderMapper;
    
    
    
    // L'AJOUT D'UNE COMMANDE 
    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrder(@RequestBody OrderDto orderDto) {
        Orders order = orderMapper.toOrder(orderDto);
        orderService.save(order);
    }
    
    // LISTE DES COMMANDES
    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> findAllOrders() {
        return orderMapper.modelsToDtos(orderService.findAll());
    }
    
    // RECHERCHER UNE COMMANDE PAR SON ID
    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto findOneOrder(@PathVariable("id") UUID id) {
        return orderMapper.toOrderDto(orderService.findOne(id));
    }
    
    // MODIFIER LES INFOS D'UNE COMMANDE
    @PutMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrder(@PathVariable("id") UUID id, @RequestBody OrderDto orderDto){
        Orders order = orderMapper.toOrder(orderDto);
        order.setOrder_id(id);
        orderService.save(order);
    }
    
    // SUPPRIMMER UN COMMANDE PAR SON ID 
    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable("id") UUID id) {
        orderService.deleteById(id);       
    }
    
    @DeleteMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllOrders() {
        orderService.deleteAll();      
    }
    
}

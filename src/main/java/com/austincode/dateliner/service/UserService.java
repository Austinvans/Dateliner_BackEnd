/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

import com.austincode.dateliner.model.User;
import com.austincode.dateliner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User save(User user){
        System.out.println("Saving new Customer");
        return userRepository.save(user);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.controller;

import com.austincode.dateliner.dto.UserDto;
import com.austincode.dateliner.mapper.UserMapper;
import com.austincode.dateliner.model.User;
import com.austincode.dateliner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
public class UserController {
    
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    UserService userService;
    
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto save(@RequestBody UserDto userDto) {
        User user = userMapper.toUser(userDto);
        userService.save(user);
        return userMapper.toUserDto(user);
    }
    
}

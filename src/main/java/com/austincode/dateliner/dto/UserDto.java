/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.dto;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

/**
 *
 * @author Yvan Ngakeu
 */

@Data
public class UserDto {
    
    @PrimaryKey
    private UUID user_id;
    
    @Column(value = "user_name")
    private String username;
    
    @Column(value = "user_surname")
    private String surname;
    
    @Column(value = "user_email")
    private String email;
    
    @Column(value = "user_password")
    private String password;
}

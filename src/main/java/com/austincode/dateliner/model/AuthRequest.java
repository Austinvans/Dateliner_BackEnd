/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Yvan Ngakeu
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    
    private String username;
    private String password;
    
}

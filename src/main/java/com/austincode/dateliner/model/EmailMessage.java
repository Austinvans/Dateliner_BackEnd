/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.model;

import lombok.Data;

/**
 *
 * @author Yvan Ngakeu
 */
@Data
public class EmailMessage {
    
    private String to;
    private String subject;
    private String message;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.configs;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Yvan Ngakeu
 */
public class SmsRequest {
 
    
    private final String phoneNumber;
    private final String message;
    
    
    public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber, @JsonProperty("message") String message){
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
    
    public String getMessage(){
        return message;
    }
    
    @Override
    public String toString(){
        return "SmsRequest{" + "phoneNumber='" + phoneNumber + '\'' + ", message='" + message + '\'' + '}';
    }
}

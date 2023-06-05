/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;    

/**
 *
 * @author Yvan Ngakeu
 */

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/dateliner")
public class SmsController {
    
    private final SmsService smsService;
    
    @Autowired
    public SmsController(SmsService smsService){
        this.smsService = smsService;
    }
    
    @PostMapping("/sms")
    public void sendSms(@RequestBody SmsRequest smsRequest){
        smsService.sendSms(smsRequest);
    }
}

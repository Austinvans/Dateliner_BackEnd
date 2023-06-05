/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service
public class SmsService {
    
    private final SmsSender smsSender;
    
    @Autowired
    public SmsService(@Qualifier("twilio") TwilioSmsSender twilioSmsSender){
        this.smsSender = twilioSmsSender;
    }
    
    public void sendSms(SmsRequest smsRequest){
        smsSender.sendSms(smsRequest);
    }
    
}

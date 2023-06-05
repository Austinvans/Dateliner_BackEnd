/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.configs;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */
@Service("twilio")
public class TwilioSmsSender implements SmsSender{
    
    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);
    
    private final TwilioConfig twilioConfig;
    
    @Autowired
    public TwilioSmsSender(TwilioConfig twilioConfig){
        this.twilioConfig = twilioConfig;
    } 
    
    @Override
    public void sendSms(SmsRequest smsRequest){
        if(isPhoneNumberValid(smsRequest.getPhoneNumber())){
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}" + smsRequest);
        }else {
            throw new IllegalArgumentException("Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid phone number");
        }
        
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
       // TODO: Implement phone number validator
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

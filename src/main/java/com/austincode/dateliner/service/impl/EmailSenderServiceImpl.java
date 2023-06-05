/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service.impl;

import com.austincode.dateliner.service.EmailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Yvan Ngakeu
 */

@Service
public class EmailSenderServiceImpl implements EmailSenderService{
    
    private final JavaMailSender mailSender;
    
    public EmailSenderServiceImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }
    
    @Override
    public void sendEmail(String to, String subject, String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("ngakeu710@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        
        this.mailSender.send(simpleMailMessage);
        
    }
    
    
}

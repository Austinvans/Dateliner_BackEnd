/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.service;

/**
 *
 * @author Yvan Ngakeu
 */
public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}

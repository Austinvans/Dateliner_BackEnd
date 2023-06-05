/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Yvan Ngakeu
 */
@Data
@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfig {
    
    private String accountSid;
    private String authToken;
    private String trialNumber;
}

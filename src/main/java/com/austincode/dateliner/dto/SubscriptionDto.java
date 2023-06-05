/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.UUID;
import lombok.Data;

/**
 *
 * @author Yvan Ngakeu
 */
@Data
public class SubscriptionDto {
    
    private UUID subscription_id;
    private float subscription_amount;
    private String subscription_duration;
    private String subscription_description;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date subscription_start_date;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date subscription_end_date;
    
}

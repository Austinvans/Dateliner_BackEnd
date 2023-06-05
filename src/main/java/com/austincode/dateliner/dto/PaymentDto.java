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
public class PaymentDto {
    
    private UUID payment_id;
    private float payment_amount;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date payment_date;
    private String payment_status;
}

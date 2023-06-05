/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.dto;

import java.util.UUID;
import lombok.Data;

/**
 *
 * @author Yvan Ngakeu
 */
@Data
public class PaymentMethodsDto {
    
    private UUID payment_mode_id;
    private String payment_mode_name;
    private String payment_mode_description;
}

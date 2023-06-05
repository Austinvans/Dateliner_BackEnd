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
public class SellerDto {
    
    private UUID seller_id;
    private String seller_name;
    private String seller_surname;
    private String seller_store;
    private String seller_store_description;
}

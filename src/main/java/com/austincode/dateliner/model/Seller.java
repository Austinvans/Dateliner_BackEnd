/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author Yvan Ngakeu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table 
public class Seller {
    
    @PrimaryKey
    private UUID seller_id;
    private String seller_name;
    private String seller_surname;
    private String seller_store;
    private String seller_store_description;
    
}

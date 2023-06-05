/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
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
public class Orders {
    // ATTRIBUTS
    @PrimaryKey
    private UUID order_id;
    private float order_amount;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date order_date;
    
    private String order_description;
    
}

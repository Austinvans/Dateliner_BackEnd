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
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author Yvan Ngakeu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("customer") 
public class Customer {
    
    @PrimaryKey
    private UUID customer_id;
    @Column(value = "customer_name")
    private String name;
    private String customer_surname;
    private String customer_tel;
    private String customer_email;
    
}

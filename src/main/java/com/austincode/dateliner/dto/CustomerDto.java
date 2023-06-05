/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.dto;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;

/**
 *
 * @author Yvan Ngakeu
 */
@Data
public class CustomerDto {
    private UUID customer_id;
    @Column(value = "customer_name")
    private String name;
    private String customer_surname;
    private String customer_tel;
    private String customer_email;
    
}

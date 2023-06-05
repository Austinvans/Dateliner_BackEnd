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
@Table("timeliner")
public class Timeliner {
    
    @PrimaryKey
    private UUID timeliner_id;
    
    @Column(value = "timeliner_name")
    private String name;
    
    private String reminder_mean_one;
    
    private String reminder_mean_two;
    
    private String reminder_mean_three;
    
    private float timeliner_amount;
    
    private String timeliner_reason;
    
    private String timeliner_email;
    
    private String timeliner_tel;
    
    private String timeliner_penalty_amount;
    
    @Column(value = "timeliner_penalty_rate")
    private String penalty;
    
    @JsonFormat(timezone="GMT+1", pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date timeliner_start_date;
    
    @JsonFormat(timezone="GMT+1", pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date timeliner_end_date;
    
    private int dateliner_number;
    
    @Column(value = "timeliner_status")
    private String status;
    
    private int timeliner_reminder_number;
            
}

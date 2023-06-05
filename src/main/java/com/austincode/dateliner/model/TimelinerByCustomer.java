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
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author Yvan Ngakeu
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("timeliner_by_customer")
public class TimelinerByCustomer {
    
    @PrimaryKeyColumn(name = "customer_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID customer_id;
    @PrimaryKeyColumn(name = "timeliner_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID timeliner_id;
    private String customer_name;
    private String customer_surname;
    private String customer_tel;
    private String customer_email;
    private float timeliner_amount;
    
    @JsonFormat(timezone="GMT+1", pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date timeliner_start_date;
    
    @JsonFormat(timezone="GMT+1", pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date timeliner_end_date;
    
    private int dateliner_number;
    private String timeliner_status;
    private int timeliner_reminder_number;
    
    
}

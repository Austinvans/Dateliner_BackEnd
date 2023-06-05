/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.MapId;

/**
 *
 * @author Yvan Ngakeu
 */
@Data
public class DatelinerByTimelinerDto {
    @Column(value = "timeliner_id")
    private UUID timeliner;
    
    private UUID dateliner_id;
    
    private float timeliner_amount;
    
    @Column(value = "timeliner_name")
    private String name;
    
    private String reminder_mean_one;
    
    private String reminder_mean_two;
    
    private String reminder_mean_three;
    
    private String timeliner_email;
    
    private String timeliner_tel;

    private float penalty_amount;
    
    private String timeliner_reason;
    
    @JsonFormat(timezone="GMT+1", pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date timeliner_start_date;
    
    @JsonFormat(timezone="GMT+1", pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date timeliner_end_date;
    
    private int dateliner_number;
    
    private String timeliner_status;
    
    private int timeliner_reminder_number;
    
    private float dateliner_amount; 
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date dateliner_start_date;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date dateliner_end_date; 
    
    private String dateliner_status;
    
    private int dateliner_reminder_time;
    
    @JsonIgnore
    public MapId getMapId() {
	return BasicMapId.id("timeliner_id", timeliner).with("dateliner_id", dateliner_id);
    }
    
}

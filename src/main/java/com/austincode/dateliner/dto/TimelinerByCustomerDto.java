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
public class TimelinerByCustomerDto {
    
    private UUID customer_id;
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

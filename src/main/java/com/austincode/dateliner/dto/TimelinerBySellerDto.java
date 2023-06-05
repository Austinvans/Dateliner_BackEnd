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
public class TimelinerBySellerDto {
    
    private UUID seller_id;
    private UUID timeliner_id;
    private String seller_name;
    private String seller_surname;
    private String seller_store;
    private String seller_store_description;
    private float timeliner_amount;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date timeliner_start_date;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date timeliner_end_date;
    private int dateliner_number;
    private String timeliner_status;
    private int timeliner_reminder_number;
}

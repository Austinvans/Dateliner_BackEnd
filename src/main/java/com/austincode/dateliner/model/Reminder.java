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
public class Reminder {
    
    @PrimaryKey
    private UUID reminder_id;
    private String reminder_message;
    private String reminder_status;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date reminder_date;
}

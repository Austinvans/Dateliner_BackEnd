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
public class BillDto {
    
    private UUID bill_id;
    private int bill_number;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="Pacific/Midway")
    private Date bill_date; 
}

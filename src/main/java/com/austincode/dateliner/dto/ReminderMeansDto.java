/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.dto;

import java.util.UUID;
import lombok.Data;

/**
 *
 * @author Yvan Ngakeu
 */
@Data
public class ReminderMeansDto {
    private UUID reminder_mean_id;
    private String reminder_mean_name;
    private String reminder_mean_description;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ObjectsByTimelinerDto {
    
    @Column(value = "timeliner_id")
    private UUID timeliner;
    
    private UUID guarantee_id;
    
    @Column(value = "timeliner_name")
    private String name;
    
    private float timeliner_amount;
    
    private String guarantee_label;
    
    private float guarantee_amount;
    
    private String guarantee_image;
    
    @JsonIgnore
    public MapId getMapId() {
	return BasicMapId.id("timeliner_id", timeliner).with("guarantee_id", guarantee_id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author Yvan Ngakeu
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("guarantee_objects_by_timeliner")
public class ObjectsByTimeliner {
    
    @PrimaryKeyColumn(name = "timeliner_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @Column(value = "timeliner_id")
    private UUID timeliner;
    
    @PrimaryKeyColumn(name = "guarantee_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
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

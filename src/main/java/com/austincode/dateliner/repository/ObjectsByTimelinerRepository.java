/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.repository;

import com.austincode.dateliner.model.ObjectsByTimeliner;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 *
 * @author Yvan Ngakeu
 */
public interface ObjectsByTimelinerRepository extends CassandraRepository<ObjectsByTimeliner, MapId>{
    
    @AllowFiltering
    List <ObjectsByTimeliner> findByName(String name);
    List<ObjectsByTimeliner> findByTimeliner(UUID id);
    
}

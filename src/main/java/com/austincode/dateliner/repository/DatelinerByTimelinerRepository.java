/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.repository;

import com.austincode.dateliner.model.DatelinerByTimeliner;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Yvan Ngakeu
 */
//public interface DatelinerByTimelinerRepository extends PagingAndSortingRepository<DatelinerByTimeliner, MapId>{
public interface DatelinerByTimelinerRepository extends CassandraRepository<DatelinerByTimeliner, MapId>{
    
    @AllowFiltering
    List <DatelinerByTimeliner> findByName(String name);
    List <DatelinerByTimeliner> findByTimeliner(UUID timeliner);
    //List <DatelinerByTimeliner> findAll(Sort sort);
    
}

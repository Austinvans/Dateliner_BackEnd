/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.repository;

import com.austincode.dateliner.model.User  ;
import java.util.UUID;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yvan Ngakeu
 */
@Repository
public interface UserRepository extends CassandraRepository<User, UUID>{
    
    @AllowFiltering
    User findByUsername(String username);
}

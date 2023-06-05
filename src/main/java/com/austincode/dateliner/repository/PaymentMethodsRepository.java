/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.repository;

import com.austincode.dateliner.model.PaymentMethods;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 *
 * @author Yvan Ngakeu
 */
public interface PaymentMethodsRepository extends CassandraRepository<PaymentMethods, UUID>{
    
}

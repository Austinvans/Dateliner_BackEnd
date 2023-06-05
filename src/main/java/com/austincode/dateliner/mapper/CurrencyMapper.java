/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.CurrencyDto;
import com.austincode.dateliner.model.Currency;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Yvan Ngakeu
 */
@Mapper(componentModel = "spring", imports = UUID.class)
public interface CurrencyMapper {
    CurrencyMapper MAPPER = Mappers.getMapper(CurrencyMapper.class);
    
    
    CurrencyDto toCurrencyDto(Currency currency);
    
    List<CurrencyDto> modelsToDtos(List<Currency> currencies);
    
    @Mapping(target = "currency_id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "currency_description", defaultValue = "OnGoing")
    Currency toCurrency(CurrencyDto currencyDto);
}

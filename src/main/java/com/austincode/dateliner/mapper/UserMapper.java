/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.austincode.dateliner.mapper;

import com.austincode.dateliner.dto.UserDto;
import com.austincode.dateliner.model.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Yvan Ngakeu
 */

@Mapper(componentModel = "spring", imports = {UUID.class, Date.class})
public interface UserMapper {
    
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    
    UserDto toUserDto(User user);
    
    List<UserDto> modelsToDtos(List<User> users);
    
    @Mapping(target = "user_id", expression = "java(UUID.randomUUID())")
    User toUser(UserDto userDto);
}

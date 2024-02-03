package com.example.quizspark.admin.mapper;

import com.example.quizspark.admin.dto.UserDTO;
import com.example.quizspark.security.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}


package com.example.red.social.redSocial_AO.InterfacesMapper;

import com.example.red.social.redSocial_AO.DTO.UserDTO;
import com.example.red.social.redSocial_AO.Models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDto);
}

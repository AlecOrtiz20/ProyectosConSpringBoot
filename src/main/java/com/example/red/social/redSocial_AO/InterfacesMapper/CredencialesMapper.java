package com.example.red.social.redSocial_AO.InterfacesMapper;

import com.example.red.social.redSocial_AO.DTO.CredencialesDTO;
import com.example.red.social.redSocial_AO.Models.Credenciales_usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredencialesMapper {

    Credenciales_usuario toEntity(CredencialesDTO credencialesDTO);
    CredencialesDTO toDTO (Credenciales_usuario credencialesUsuario);
}

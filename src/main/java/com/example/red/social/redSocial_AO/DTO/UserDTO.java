package com.example.red.social.redSocial_AO.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String username;
    private Date fecha_nacimiento;

    @JsonProperty("bio_usuario")
    private String bio_usuario;
    private Date fechaCreacion;


}

package com.example.red.social.redSocial_AO.DTO;

import com.example.red.social.redSocial_AO.Enums.RoleUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CredencialesDTO {
    private String email;

    @JsonProperty("password")
        private String password;

    private RoleUser role;
}

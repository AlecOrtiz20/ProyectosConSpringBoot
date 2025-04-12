package com.example.red.social.redSocial_AO.Security.JWT;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthenticationResponse {
   private String token;
}

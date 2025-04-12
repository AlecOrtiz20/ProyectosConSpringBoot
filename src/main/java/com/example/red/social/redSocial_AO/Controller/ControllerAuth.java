package com.example.red.social.redSocial_AO.Controller;

import com.example.red.social.redSocial_AO.Security.JWT.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class ControllerAuth {

    private final JwtService jwtService;

    public ControllerAuth(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String correo){
        String token = jwtService.generateToken(correo);

        return ResponseEntity.ok(token);
    }
}

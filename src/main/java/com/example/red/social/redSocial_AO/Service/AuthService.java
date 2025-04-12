package com.example.red.social.redSocial_AO.Service;

import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Repository.CredencialesRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CredencialesRepository credencialesRepository;

    public AuthService(CredencialesRepository credencialesRepository) {
        this.credencialesRepository = credencialesRepository;
    }

    private String getUsernameFromToken(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails){
            return userDetails.getUsername();
        }

        return null;
    }

    public User getUserFromToken(){
        String email = getUsernameFromToken();

        return this.credencialesRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("El usuario no existe")
        ).getUserCredenciales();
    }
}

package com.example.red.social.redSocial_AO.Security;

import com.example.red.social.redSocial_AO.Models.Credenciales_usuario;
import com.example.red.social.redSocial_AO.Repository.CredencialesRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final CredencialesRepository credencialesRepository;

    public UserDetailService(CredencialesRepository credencialesRepository) {
        this.credencialesRepository = credencialesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Credenciales_usuario credencialesUsuario = this.credencialesRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("El usuario con las credenciales no existen..."));

        return new UserDetailImpl(credencialesUsuario);
    }
}

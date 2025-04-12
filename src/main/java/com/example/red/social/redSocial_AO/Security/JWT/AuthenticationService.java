package com.example.red.social.redSocial_AO.Security.JWT;

import com.example.red.social.redSocial_AO.Configuration.PasswordEncoderConfig;
import com.example.red.social.redSocial_AO.DTO.CredencialesDTO;
import com.example.red.social.redSocial_AO.DTO.CredentialsDTO;
import com.example.red.social.redSocial_AO.Models.Credenciales_usuario;
import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Repository.CredencialesRepository;
import com.example.red.social.redSocial_AO.Repository.UserRepository;
import com.example.red.social.redSocial_AO.exception.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final CredencialesRepository credencialesRepository;
    private final JwtService jwtService;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager, CredencialesRepository credencialesRepository, JwtService jwtService, PasswordEncoderConfig passwordEncoderConfig) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.credencialesRepository = credencialesRepository;
        this.jwtService = jwtService;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }


    public AuthenticationResponse register(CredentialsDTO credentialsDTO){

        var user = User.builder()
                .nombre(credentialsDTO.getUser().getNombre())
                .apellido(credentialsDTO.getUser().getApellido())
                .username(credentialsDTO.getUser().getUsername())
                .fecha_nacimiento(credentialsDTO.getUser().getFecha_nacimiento())
                .bio_usuario(credentialsDTO.getUser().getBio_usuario())
                .fechaCreacion(new Date())
                .build();

        var credentials = Credenciales_usuario.builder()
                .password(this.passwordEncoderConfig.passwordEncoder().encode(credentialsDTO.getCredencialesDTO().getPassword()))
                .email(credentialsDTO.getCredencialesDTO().getEmail())
                .fechaCreacion(new Date())
                .role(credentialsDTO.getCredencialesDTO().getRole())
                .userCredenciales(user)
                .build();

        this.userRepository.save(user);
        this.credencialesRepository.save(credentials);

        var token = this.jwtService.generateToken(credentials.getEmail());

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse athenticcate(CredencialesDTO credencialesDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credencialesDTO.getEmail(),
                        credencialesDTO.getPassword()
                )
        );

        var user = this.credencialesRepository.findByEmail(credencialesDTO.getEmail())
                .map(Credenciales_usuario::getUserCredenciales)
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));

        var token = jwtService.generateToken(credencialesDTO.getEmail());

        return AuthenticationResponse.builder()
                .token(token)
                .build();

    }
}

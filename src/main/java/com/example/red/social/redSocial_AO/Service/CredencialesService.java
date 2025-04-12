package com.example.red.social.redSocial_AO.Service;

import com.example.red.social.redSocial_AO.Configuration.PasswordEncoderConfig;
import com.example.red.social.redSocial_AO.DTO.CredencialesDTO;
import com.example.red.social.redSocial_AO.DTO.UserDTO;
import com.example.red.social.redSocial_AO.InterfacesMapper.CredencialesMapper;
import com.example.red.social.redSocial_AO.InterfacesMapper.UserMapper;
import com.example.red.social.redSocial_AO.Models.Credenciales_usuario;
import com.example.red.social.redSocial_AO.Repository.CredencialesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CredencialesService {

    private static final Logger logger = LoggerFactory.getLogger(CredencialesService.class);

    private final CredencialesRepository credencialesRepository;
    private final PasswordEncoderConfig encryptarClave;
    private final CredencialesMapper credencialesMapper;
    private final UserMapper userMapper;

    public CredencialesService(CredencialesRepository credencialesRepository, PasswordEncoderConfig encryptarClave,  CredencialesMapper credencialesMapper, UserMapper userMapper) {
        this.credencialesRepository = credencialesRepository;
        this.encryptarClave = encryptarClave;
        this.credencialesMapper = credencialesMapper;
        this.userMapper = userMapper;
    }

    @Transactional
    public void guardarCredencialesUsuario(UserDTO user, CredencialesDTO credencialesDTO){

        Credenciales_usuario credencialesGuardar = new Credenciales_usuario();

        credencialesGuardar.setEmail(credencialesDTO.getEmail());
        credencialesGuardar.setPassword(this.encryptarClave.passwordEncoder().encode(credencialesDTO.getPassword()));

        logger.info("Este es el correo: " + credencialesGuardar.getEmail());

        if(credencialesGuardar.getPassword() == null){
            logger.error("La clave es nula: " );
            throw new IllegalArgumentException("La clave no puede ser nula");
        }


        credencialesGuardar.setUserCredenciales(this.userMapper.toEntity(user));
        credencialesGuardar.setFechaCreacion(new Date());
        this.credencialesRepository.save(credencialesGuardar);
    }
}

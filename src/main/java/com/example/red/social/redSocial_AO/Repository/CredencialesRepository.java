package com.example.red.social.redSocial_AO.Repository;

import com.example.red.social.redSocial_AO.Models.Credenciales_usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredencialesRepository extends JpaRepository<Credenciales_usuario, Long> {

    //Credenciales_usuario findByEmail(String email);
    Optional<Credenciales_usuario>  findByEmail(String email);
}

package com.example.red.social.redSocial_AO.Models;

import com.example.red.social.redSocial_AO.Enums.RoleUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credenciales_usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuarioCredenciales", nullable = false)
    private User userCredenciales;

    @Size(max = 100, message = "El correo no puede tener mas de 100 letras")
    private String email;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Enumerated(EnumType.STRING)
    private RoleUser role;


}

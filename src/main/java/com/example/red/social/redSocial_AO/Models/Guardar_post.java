package com.example.red.social.redSocial_AO.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Guardar_post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userGuardado", nullable = false)
    private User userGuardado;

    @ManyToOne
    @JoinColumn(name = "postGuardado", nullable = false)
    private Post postGuardado;

    @Temporal(TemporalType.DATE)
    private Date fechaGuardado;

}

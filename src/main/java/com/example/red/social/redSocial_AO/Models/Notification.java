package com.example.red.social.redSocial_AO.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "notificaciones")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private boolean leido = false;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion = new Date();

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;


}

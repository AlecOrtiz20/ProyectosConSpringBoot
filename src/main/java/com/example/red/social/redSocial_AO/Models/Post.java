package com.example.red.social.redSocial_AO.Models;

import com.example.red.social.redSocial_AO.Enums.EstadoPost;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenidoPost;

    @ManyToOne
    @JoinColumn(name = "post_user")
    private User user;

    private Integer likesPost = 0;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;

    private Integer visitas = 0;

    @Enumerated(EnumType.STRING)
    private EstadoPost estadoPost;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentariosPost = new ArrayList<>();

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;


}

package com.example.red.social.redSocial_AO.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "user_like")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "like_post")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "like_usuario", nullable = false)
    private User user;

    @Temporal(TemporalType.DATE)    private Date fechaLike;

}

package com.example.red.social.redSocial_AO.DTO;

import com.example.red.social.redSocial_AO.Enums.EstadoPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PostDTO {

    /*private Long id;
    private String contenido_post;
    private Long userId;
    private Date fechaActualizacion;
    private Integer likesPost = 0;
    private Integer visitas = 0;
    private EstadoPost estadoPost;
    private Date fechaCreacion;*/

    private Long id;
    private String contenidoPost;
    private Long userId;
    private Integer likesPost;
    private Date fechaActualizacion;
    private Integer visitas;
    private EstadoPost estadoPost;
    private Date fechaCreacion;

}

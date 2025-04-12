package com.example.red.social.redSocial_AO.Service;

import com.example.red.social.redSocial_AO.DTO.CommentDTO;
import com.example.red.social.redSocial_AO.Models.Comentario;
import com.example.red.social.redSocial_AO.Models.Post;
import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Repository.CommentRepository;
import com.example.red.social.redSocial_AO.Repository.PostRepository;
import com.example.red.social.redSocial_AO.ServiceInterfaces.ICommentPostService;
import com.example.red.social.redSocial_AO.exception.CommentNotFoundException;
import com.example.red.social.redSocial_AO.exception.PostNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ComentService implements ICommentPostService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private Logger logger = LoggerFactory.getLogger(ComentService.class);


    public ComentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;

        this.postRepository = postRepository;
    }

    @Override
    public void addCommentPost(CommentDTO commentDTO, User userByComment) {

        try {
            //Se obtiene el post al cual se le va a agregar el comentario
            Post post = this.postRepository.findById(commentDTO.getId())
                    .orElseThrow(() -> new PostNotFoundException("El post con el id: " + commentDTO.getId() + " no existe"));

            if (commentDTO.getContenido() == null || commentDTO.getContenido().isEmpty()){
                throw new CommentNotFoundException("El comentario no puede estar vacio...");
            }

            Comentario comentario = new Comentario();

            comentario.setPost(post);
            comentario.setContenido_post(commentDTO.getContenido());
            comentario.setUser(userByComment);
            comentario.setFecha_creacion(new Date());
            this.commentRepository.save(comentario);
            logger.info("El comtario se publico correctamente...");
        }catch (Exception e){
            logger.error("Error al pubiclar el comentario: " + e.getMessage());
        }




    }
}

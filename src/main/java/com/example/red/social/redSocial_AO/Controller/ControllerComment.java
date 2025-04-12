package com.example.red.social.redSocial_AO.Controller;

import com.example.red.social.redSocial_AO.DTO.CommentDTO;
import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Repository.UserRepository;
import com.example.red.social.redSocial_AO.Service.ComentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/")
public class ControllerComment {


    private final ComentService comentService;
    private final UserRepository userRepository;

    public ControllerComment(ComentService comentService, UserRepository userRepository) {
        this.comentService = comentService;
        this.userRepository = userRepository;
    }

    @PostMapping("/comment")
    public ResponseEntity<Map<String, Object>> addComment(@RequestBody CommentDTO commentDTO){
        Map<String, Object> response = new HashMap<>();

        try {
            User userComment = this.userRepository.findById(commentDTO.getId())
                    .orElseThrow(() -> new RuntimeException("El usuario no existe"));


            this.comentService.addCommentPost(commentDTO, userComment);

            response.put("message", "Comentario publicado con exito");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("error", "Error al publicar el comentario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

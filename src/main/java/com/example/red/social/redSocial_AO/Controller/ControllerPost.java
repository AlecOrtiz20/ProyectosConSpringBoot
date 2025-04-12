package com.example.red.social.redSocial_AO.Controller;

import com.example.red.social.redSocial_AO.DTO.LikeDTO;
import com.example.red.social.redSocial_AO.DTO.PostDTO;
import com.example.red.social.redSocial_AO.Enums.EstadoPost;
import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Service.AuthService;
import com.example.red.social.redSocial_AO.Service.LikeService;
import com.example.red.social.redSocial_AO.Service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class ControllerPost {
    private final PostService postService;
    private final LikeService likeService;
    private final AuthService authService;

    public ControllerPost(PostService postService, LikeService likeService, AuthService authService) {
        this.postService = postService;
        this.likeService = likeService;
        this.authService = authService;
    }

    @PostMapping("/post")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody PostDTO postDTO){
        Map<String, Object> response = new HashMap<>();

        try {

            User user = this.authService.getUserFromToken();

            PostDTO postCreate = this.postService.create(postDTO, user.getId());

            response.put("message: ", "Post Creado con exito");
            response.put("Post: ", postCreate );
            return ResponseEntity.ok(response);
        }catch (Exception e){
            response.put("Error al crear el post", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }


    }

    @GetMapping("/posts")
    public PageResponse  getAllPost(@RequestParam EstadoPost estadoPost, @PageableDefault(size = 10, sort = "fechaCreacion")Pageable pageable){
        try{
            Page<PostDTO> posts = this.postService.findAll(estadoPost, pageable);
            return new PageResponse(posts.getContent(), posts.getTotalPages(), posts.getTotalElements());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public record PageResponse(List<PostDTO> content, int totalPages, long totalElements) {}

    @PostMapping("/updatePost")
    public ResponseEntity<Map<String, Object>> editPost(@RequestBody PostDTO postDTO){
        Map<String, Object> response = new HashMap<>();
        try {
            this.postService.update(postDTO);

            response.put("message", "Post actualizado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al actualizar el post: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

        }

    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<Map<String, Object>> deletePost(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();
        try{

            this.postService.delete(id);

            response.put("message", "Post eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al eliminar el post: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @PostMapping("/like")
    public ResponseEntity<Map<String, Object>> likePost(@RequestBody LikeDTO likeDTO){
        Map<String, Object> response = new HashMap<>();
        try {
            User user = this.authService.getUserFromToken();
            this.likeService.likePost(likeDTO, user);
            response.put("message", "Like dado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "No se pudo dar like, ya le diste like: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/profileUserPosts")
    public PageResponse getAllPotsByUser(@RequestParam(name = "statusPosts") EstadoPost statusPots, @PageableDefault(size = 10) Pageable pageable){

        try{
            User user = this.authService.getUserFromToken();
            Page<PostDTO> postDTOS = this.postService.postByUser(statusPots, pageable, user);
            return new PageResponse(postDTOS.getContent(), postDTOS.getTotalPages(), postDTOS.getTotalElements());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}

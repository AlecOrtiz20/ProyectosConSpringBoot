package com.example.red.social.redSocial_AO.Service;

import com.example.red.social.redSocial_AO.DTO.PostDTO;
import com.example.red.social.redSocial_AO.Enums.EstadoPost;
import com.example.red.social.redSocial_AO.InterfacesMapper.PostMapper;
import com.example.red.social.redSocial_AO.Models.Post;
import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Repository.PostRepository;
import com.example.red.social.redSocial_AO.Repository.UserRepository;
import com.example.red.social.redSocial_AO.ServiceInterfaces.ICrudService;
import com.example.red.social.redSocial_AO.exception.PostNotFoundException;
import com.example.red.social.redSocial_AO.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PostService implements ICrudService<PostDTO, Post> {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    public PostService(PostRepository postRepository, PostMapper postMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userRepository = userRepository;

    }

    public User findByUserId(Long idUser){

        //obtener el usuario por su id
        return this.userRepository.findById(idUser)
                .orElseThrow(() -> {
                    logger.error("El usuario con el id: " + idUser + " no existe");
                    return new UserNotFoundException("El usuario con el id: " + idUser + " no existe..");
                });
    }


    @Override
    public PostDTO create(PostDTO postDTO, Long idUser) {

        User userByPost = this.findByUserId(idUser);

        Post post = this.postMapper.toPostEntity(postDTO);
        post.setUser(userByPost);
        post.setFechaCreacion(new Date());
        post.setContenidoPost(postDTO.getContenidoPost());
        post.setLikesPost(0);
        post.setVisitas(0);
        post.setEstadoPost(EstadoPost.ACTIVO);
        this.postRepository.save(post);

        return postDTO;
    }

    @Override
    public void update(PostDTO postDTO) {

        Post postUpdate = findById(postDTO.getId());
        postUpdate.setContenidoPost(postDTO.getContenidoPost());
        postUpdate.setFechaActualizacion(new Date());
        this.postRepository.save(postUpdate);
    }

    @Override
    public void delete(Long id) {
        Post post = findById(id);
        post.setEstadoPost(EstadoPost.ELIMINADO);
        this.postRepository.save(post);
    }

    @Override
    public Page<PostDTO> findAll(EstadoPost estadoPost,Pageable pageable) {

        Page<Post> posts = this.postRepository.findByEstadoPost(estadoPost, pageable);

        return posts.map(post -> new PostDTO(post.getId(), post.getContenidoPost(),
            post.getUser().getId(), post.getLikesPost(), post.getFechaActualizacion(), post.getVisitas(),
                post.getEstadoPost(), post.getFechaCreacion()
        ));
       /* return this.postRepository.findByEstadoPost(estadoPost, pageable)
                .map(this.postMapper::ToPostDto);*/

    }

    @Override
    public Post findById(Long id) {

        return this.postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("El post No existe"));
    }


    public Page<PostDTO> postByUser(EstadoPost status, Pageable pageable, User user){
        Page<Post> postPage = this.postRepository.findByUserAndEstadoPost(user, status, pageable);
        Page<PostDTO> postDTOS = postPage.map(
                post -> new PostDTO(post.getId(), post.getContenidoPost(),post.getUser().getId(), post.getLikesPost(), post.getFechaActualizacion(), post.getVisitas(), post.getEstadoPost(), post.getFechaCreacion())
        );

        return postDTOS;
    }
}

package com.example.red.social.redSocial_AO.Service;

import com.example.red.social.redSocial_AO.DTO.LikeDTO;
import com.example.red.social.redSocial_AO.Models.Like;
import com.example.red.social.redSocial_AO.Models.Post;
import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Repository.LikeRepository;
import com.example.red.social.redSocial_AO.Repository.PostRepository;
import com.example.red.social.redSocial_AO.ServiceInterfaces.ILikeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LikeService implements ILikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public void likePost(LikeDTO likeDTO, User user) {
        Post postLike = this.postRepository.findById(likeDTO.getIdpostLike())
                .orElseThrow(() -> new RuntimeException("El post con el id: " + likeDTO.getIdpostLike() + " No xiste"));

        if(this.likeRepository.existsByUserAndPost(user, postLike)){
            throw new RuntimeException("Ya le has dado like a esta publicacion");
        }

        incrementarLike(postLike);

        Like like = new Like();
        like.setFechaLike(new Date());
        like.setPost(postLike);
        like.setUser(user);
        this.likeRepository.save(like);
    }


    public void incrementarLike(Post postLike){
        postLike.setLikesPost(postLike.getLikesPost() + 1);
        this.postRepository.save(postLike);
    }


}


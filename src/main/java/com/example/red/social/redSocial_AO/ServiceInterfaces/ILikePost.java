package com.example.red.social.redSocial_AO.ServiceInterfaces;

import com.example.red.social.redSocial_AO.Models.Post;

public interface ILikePost {

    Post getPostId(Long idPost);
    void incrementarLike(Post post);
}

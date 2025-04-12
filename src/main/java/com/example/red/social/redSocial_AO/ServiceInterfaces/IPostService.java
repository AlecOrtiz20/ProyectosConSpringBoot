package com.example.red.social.redSocial_AO.ServiceInterfaces;

import com.example.red.social.redSocial_AO.DTO.PostDTO;
import com.example.red.social.redSocial_AO.Enums.EstadoPost;
import com.example.red.social.redSocial_AO.Models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService extends ICrudService<PostDTO, Post>{
    Page<Post> findByEstadoPost(EstadoPost estadoPost, Pageable pageable);
}

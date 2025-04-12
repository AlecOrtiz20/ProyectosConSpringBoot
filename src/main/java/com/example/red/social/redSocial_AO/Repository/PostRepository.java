package com.example.red.social.redSocial_AO.Repository;

import com.example.red.social.redSocial_AO.Enums.EstadoPost;
import com.example.red.social.redSocial_AO.Models.Post;
import com.example.red.social.redSocial_AO.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByEstadoPost(EstadoPost estadoPost, Pageable pageable);
    Page<Post> findByUserAndEstadoPost(User user, EstadoPost estadoPost, Pageable pageable);
}

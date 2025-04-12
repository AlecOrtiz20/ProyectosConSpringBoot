package com.example.red.social.redSocial_AO.Repository;

import com.example.red.social.redSocial_AO.Models.Like;
import com.example.red.social.redSocial_AO.Models.Post;
import com.example.red.social.redSocial_AO.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(User user, Post post);
}

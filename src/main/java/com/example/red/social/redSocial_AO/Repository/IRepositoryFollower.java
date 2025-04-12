package com.example.red.social.redSocial_AO.Repository;

import com.example.red.social.redSocial_AO.Models.Follower;
import com.example.red.social.redSocial_AO.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRepositoryFollower extends JpaRepository<Follower, Long> {
    boolean  existsByUserAndFollower(User user, User UserFollower);
    Optional<Follower> findByUserIdAndFollowerId(Long userId, Long followerId);
}

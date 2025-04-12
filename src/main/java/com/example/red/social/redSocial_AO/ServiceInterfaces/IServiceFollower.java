package com.example.red.social.redSocial_AO.ServiceInterfaces;

import com.example.red.social.redSocial_AO.DTO.FollowerDTO;

public interface IServiceFollower {
    void FollowerUser(FollowerDTO followerDTO);
    void unFollowerUser(Long userId, Long unFollower);
}

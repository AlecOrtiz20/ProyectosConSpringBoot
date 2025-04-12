package com.example.red.social.redSocial_AO.ServiceInterfaces;

import com.example.red.social.redSocial_AO.DTO.LikeDTO;
import com.example.red.social.redSocial_AO.Models.User;

public interface ILikeService {
    void likePost(LikeDTO likeDTO, User user);
}

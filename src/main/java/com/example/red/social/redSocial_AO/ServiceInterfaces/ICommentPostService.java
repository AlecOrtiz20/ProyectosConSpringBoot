package com.example.red.social.redSocial_AO.ServiceInterfaces;

import com.example.red.social.redSocial_AO.DTO.CommentDTO;
import com.example.red.social.redSocial_AO.Models.User;

public interface ICommentPostService {
    void addCommentPost(CommentDTO commentDTO, User userByComment);
}

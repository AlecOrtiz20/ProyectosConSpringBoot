package com.example.red.social.redSocial_AO.InterfacesMapper;

import com.example.red.social.redSocial_AO.DTO.PostDTO;
import com.example.red.social.redSocial_AO.Models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "user.id", target = "userId")
    PostDTO ToPostDto(Post post);
    @Mapping(source = "userId", target = "user.id")
    Post toPostEntity(PostDTO postDTO);

}

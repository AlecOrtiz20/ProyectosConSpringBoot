package com.example.red.social.redSocial_AO.Configuration;

import com.example.red.social.redSocial_AO.InterfacesMapper.CredencialesMapper;
import com.example.red.social.redSocial_AO.InterfacesMapper.PostMapper;
import com.example.red.social.redSocial_AO.InterfacesMapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UserMapper userMapper(){
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public PostMapper postMapper(){
        return Mappers.getMapper(PostMapper.class);
    }

    @Bean
    public CredencialesMapper credencialesMapper(){return Mappers.getMapper(CredencialesMapper.class);}

}

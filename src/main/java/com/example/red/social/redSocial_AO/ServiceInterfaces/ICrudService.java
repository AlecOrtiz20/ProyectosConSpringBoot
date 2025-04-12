package com.example.red.social.redSocial_AO.ServiceInterfaces;

import com.example.red.social.redSocial_AO.Enums.EstadoPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrudService <DTO, Entity>{
    DTO create(DTO dto, Long idUser);
    void update(DTO dto);
    void delete(Long id);
    Page<DTO> findAll(EstadoPost estadoPost, Pageable pageable);
    Entity findById(Long id);
}

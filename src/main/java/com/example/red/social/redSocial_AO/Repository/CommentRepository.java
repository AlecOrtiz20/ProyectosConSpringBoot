package com.example.red.social.redSocial_AO.Repository;

import com.example.red.social.redSocial_AO.Models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comentario, Long> {
}

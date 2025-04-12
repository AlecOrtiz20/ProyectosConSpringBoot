package com.example.red.social.redSocial_AO.Repository;

import com.example.red.social.redSocial_AO.Models.Notification;
import com.example.red.social.redSocial_AO.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndLeidoFalse(User user);
}

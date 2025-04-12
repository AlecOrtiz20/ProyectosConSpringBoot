package com.example.red.social.redSocial_AO.ServiceInterfaces;

import com.example.red.social.redSocial_AO.Models.Notification;
import com.example.red.social.redSocial_AO.Models.User;

import java.util.List;

public interface IServiceNotification {
    void createNotification(User user, String message);
    List<Notification> getUnread(User user);
    void markAsRead(List<Notification> notifications);
}

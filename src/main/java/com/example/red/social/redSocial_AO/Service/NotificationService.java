package com.example.red.social.redSocial_AO.Service;

import com.example.red.social.redSocial_AO.Models.Notification;
import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Repository.INotificationRepository;
import com.example.red.social.redSocial_AO.ServiceInterfaces.IServiceNotification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements IServiceNotification {

    private final INotificationRepository notificationRepository;

    public NotificationService(INotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    @Override
    public void createNotification(User user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        this.notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getUnread(User user) {
        return this.notificationRepository.findByUserAndLeidoFalse(user);
    }

    @Override
    public void markAsRead(List<Notification> notifications) {

        for(Notification n: notifications){
            n.setLeido(true);
        }

        this.notificationRepository.saveAll(notifications);

    }
}

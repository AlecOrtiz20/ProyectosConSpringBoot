package com.example.red.social.redSocial_AO.Controller;

import com.example.red.social.redSocial_AO.Models.Notification;
import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Service.NotificationService;
import com.example.red.social.redSocial_AO.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/")
public class ControllerNotification {

    private final NotificationService notificationService;
    private final UserService userService;

    public ControllerNotification(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getNotifications(@PathVariable Long userId){
        Map<String, Object> response = new HashMap<>();

        try {
            User userNotification = this.userService.findById(userId);
            List<Notification> notifications = this.notificationService.getUnread(userNotification);

            response.put("notificaciones", notifications);
            response.put("message", "Notificacions obtenidas con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al obtener las notificaciones: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @PostMapping("/{idUser}")
    public ResponseEntity<Map<String, Object>> markRead(@PathVariable Long idUser){

        Map<String, Object> response = new HashMap<>();

        try {
            User user = this.userService.findById(idUser);
            List<Notification> notifications = this.notificationService.getUnread(user);
            this.notificationService.markAsRead(notifications);
            response.put("message", "Notificacion marcada como leida");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error al marcar como leida");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}

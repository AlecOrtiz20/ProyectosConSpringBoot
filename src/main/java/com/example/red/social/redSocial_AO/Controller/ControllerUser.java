package com.example.red.social.redSocial_AO.Controller;

import com.example.red.social.redSocial_AO.DTO.CredencialesDTO;
import com.example.red.social.redSocial_AO.DTO.CredentialsDTO;
import com.example.red.social.redSocial_AO.DTO.FollowerDTO;
import com.example.red.social.redSocial_AO.DTO.UserDTO;
import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Repository.UserRepository;
import com.example.red.social.redSocial_AO.Security.JWT.AuthenticationResponse;
import com.example.red.social.redSocial_AO.Security.JWT.AuthenticationService;
import com.example.red.social.redSocial_AO.Service.CredencialesService;
import com.example.red.social.redSocial_AO.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class ControllerUser {

    private final UserService userService;
    private final CredencialesService credencialesService;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private Map<String, Object> response = new HashMap<>();

    public ControllerUser(UserService userService, CredencialesService credencialesService, AuthenticationService authenticationService, UserRepository userRepository) {
        this.userService = userService;
        this.credencialesService = credencialesService;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> crearUsuario(@RequestBody CredentialsDTO credentials){

        try {
            AuthenticationResponse response1 = this.authenticationService.register(credentials);
            return ResponseEntity.ok(response1);
        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear un usuario: " + e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody CredencialesDTO credencialesDTO){
        AuthenticationResponse authenticationResponse = authenticationService.athenticcate(credencialesDTO);
        System.out.println("ROL DEL USUARIO: " + credencialesDTO.getRole());
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/follower")
    public ResponseEntity<Map<String, Object>> followerUser(@RequestBody FollowerDTO followerDTO){
        try{
            this.userService.FollowerUser(followerDTO);
            response.put("Message", "Usuario seguido con exito");
            return ResponseEntity.ok(this.response);
        } catch (Exception e) {
            response.put("error", "No se puede seguir al usuario: " +  e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.response);
        }

    }

    @GetMapping("/pruebas")
    public ResponseEntity<List<UserDTO>> prueba(){
        List<User> userList = this.userRepository.findAll();

        List<UserDTO> userDTOS = userList.stream().map(
                user -> new UserDTO(user.getId(), user.getNombre(), user.getApellido(), user.getUsername(), user.getFecha_nacimiento(), user.getBio_usuario(), user.getFechaCreacion())
        ).toList();

        return ResponseEntity.ok(userDTOS);
    }




}

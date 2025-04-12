package com.example.red.social.redSocial_AO.Service;

import com.example.red.social.redSocial_AO.DTO.FollowerDTO;
import com.example.red.social.redSocial_AO.DTO.UserDTO;
import com.example.red.social.redSocial_AO.Enums.EstadoPost;
import com.example.red.social.redSocial_AO.InterfacesMapper.UserMapper;
import com.example.red.social.redSocial_AO.Models.Follower;
import com.example.red.social.redSocial_AO.Models.User;
import com.example.red.social.redSocial_AO.Repository.IRepositoryFollower;
import com.example.red.social.redSocial_AO.Repository.UserRepository;
import com.example.red.social.redSocial_AO.ServiceInterfaces.ICrudService;
import com.example.red.social.redSocial_AO.ServiceInterfaces.IServiceFollower;
import com.example.red.social.redSocial_AO.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements IServiceFollower, ICrudService<UserDTO, User> {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final IRepositoryFollower repositoryFollower;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserMapper userMapper, UserRepository userRepository, IRepositoryFollower repositoryFollower) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.repositoryFollower = repositoryFollower;
    }

    public UserDTO createUser(UserDTO userSave){
        User user = this.userMapper.toEntity(userSave);
        this.userRepository.save(user);

        return this.userMapper.toDTO(user);
    }

    @Override
    public void FollowerUser(FollowerDTO followerDTO) {
        User usuario = findById(followerDTO.getUserId());
        User follower = findById(followerDTO.getUserFollowerId());

        boolean yaSigue = this.repositoryFollower.existsByUserAndFollower(usuario, follower);

        if (yaSigue){
            throw new IllegalArgumentException("Ya sigues a este usuario.");
        }

        Follower newFollower = new Follower();
        newFollower.setUser(usuario);
        newFollower.setFollower(follower);
        newFollower.setFecha_seguido(new Date());
        this.repositoryFollower.save(newFollower);


    }

    @Override
    public void unFollowerUser(Long userId, Long unFollowerId){
        Optional<Follower> followerOptional = this.repositoryFollower.findByUserIdAndFollowerId(userId, unFollowerId);;

        try {
            followerOptional.ifPresent(this.repositoryFollower::delete);
            logger.info("Has dejado de seguir al usuario: " + followerOptional.get().getUser());
        }catch (Exception e){
            logger.error("Error al dejar de seguir");
        }
    }

    @Override
    public UserDTO create(UserDTO userDTO, Long idUser) {
        return null;
    }

    @Override
    public void update(UserDTO userDTO) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<UserDTO> findAll(EstadoPost estadoPost, Pageable pageable) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("El usuario no existe"));
    }
}

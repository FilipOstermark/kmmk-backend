package music.kmmk.backend.user.controller;

import music.kmmk.backend.common.Constants;
import music.kmmk.backend.common.dto.MessageDto;
import music.kmmk.backend.user.data.UserEntity;
import music.kmmk.backend.user.data.UserRepository;
import music.kmmk.backend.user.dto.UserDto;
import music.kmmk.backend.user.dto.UserListDto;
import music.kmmk.backend.user.mapper.UserEntityDtoMapper;
import music.kmmk.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(Constants.API_V1_URI + "/user")
public class UserController {

    public static final String DELETED_USER_NAME = "[Deleted user]";

    private final UserRepository userRepository;
    private final UserEntityDtoMapper userEntityDtoMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository,
                          UserEntityDtoMapper userEntityDtoMapper,
                          UserService userService) {
        this.userRepository = userRepository;
        this.userEntityDtoMapper = userEntityDtoMapper;
        this.userService = userService;
    }

    @GetMapping("/self")
    public UserDto getSelf() {

        final UserEntity userEntity = this.userService
                .getAuthenticatedUser()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return this.userEntityDtoMapper.toDto(userEntity);
    }

    @DeleteMapping("/self")
    public MessageDto deleteSelf() {
        final UserEntity userEntity = this.userService
                .getAuthenticatedUser()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Rather than delete the user itself, clear their personal data
        // This allows for recovery if done by mistake
        userEntity.setName(DELETED_USER_NAME);
        userEntity.setEmail(DELETED_USER_NAME);
        this.userRepository.save(userEntity);

        return new MessageDto("Successfully deleted user with ID " + userEntity.getId());
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Long id) {
        final UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return this.userEntityDtoMapper.toDto(userEntity);
    }

    @GetMapping("/list")
    public UserListDto getAllUsers() {
        // TODO Implement proper pagination
        List<UserDto> users = this.userEntityDtoMapper.toDtoList(
                this.userRepository.findAll()
        );
        return new UserListDto(
            users.size(), 0, 0, users
        );
    }
}

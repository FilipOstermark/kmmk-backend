package music.kmmk.backend.user.controller;

import music.kmmk.backend.user.data.UserEntity;
import music.kmmk.backend.user.data.UserRepository;
import music.kmmk.backend.user.dto.UserDto;
import music.kmmk.backend.user.dto.UserListDto;
import music.kmmk.backend.user.mapper.UserEntityDtoMapper;
import music.kmmk.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

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

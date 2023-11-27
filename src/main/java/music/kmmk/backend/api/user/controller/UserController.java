package music.kmmk.backend.api.user.controller;

import music.kmmk.backend.api.Constants;
import music.kmmk.backend.api.dto.MessageDto;
import music.kmmk.backend.api.user.dto.UserDto;
import music.kmmk.backend.api.user.dto.UserListDto;
import music.kmmk.backend.api.user.mapper.UserModelDtoMapper;
import music.kmmk.backend.core.user.model.User;
import music.kmmk.backend.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(Constants.API_V1_URI + "/user")
public class UserController {


    private final UserService userService;
    private final UserModelDtoMapper userModelDtoMapper;

    @Autowired
    public UserController(UserModelDtoMapper userModelDtoMapper,
                          UserService userService) {
        this.userModelDtoMapper = userModelDtoMapper;
        this.userService = userService;
    }

    @GetMapping("/self")
    public UserDto getSelf() {

        final User user = userService
                .getAuthenticatedUser()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return userModelDtoMapper.modelToDto(user);
    }

    @DeleteMapping("/self")
    public MessageDto deleteSelf() {
        User user = userService
                .deleteAuthenticatedUser()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new MessageDto("Successfully deleted user with ID " + user.id());
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") Long id) {
        final User user = userService
                .getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return this.userModelDtoMapper.modelToDto(user);
    }

    @GetMapping("/list")
    public UserListDto getAllUsers() {
        // TODO Implement proper pagination
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = this.userModelDtoMapper.modelsToDtos(users);
        return new UserListDto(users.size(), 0, 0, userDtos);
    }
}
package music.kmmk.backend.api.user.mapper;

import music.kmmk.backend.api.mapper.ModelDtoMapper;
import music.kmmk.backend.api.user.dto.UserDto;
import music.kmmk.backend.core.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserModelDtoMapper implements ModelDtoMapper<User, UserDto> {
    @Override
    public UserDto modelToDto(User user) {
        return new UserDto(
                user.id(),
                user.name(),
                user.email()
        );
    }

    @Override
    public User dtoToModel(UserDto userDto) {
        return new User(
                userDto.id(),
                userDto.name(),
                userDto.email()
        );
    }
}

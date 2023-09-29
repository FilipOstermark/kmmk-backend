package music.kmmk.backend.user.mapper;

import music.kmmk.backend.common.mapper.EntityDtoMapper;
import music.kmmk.backend.user.data.UserEntity;
import music.kmmk.backend.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserEntityDtoMapper implements EntityDtoMapper<UserEntity, UserDto> {

    @Override
    public UserEntity toEntity(UserDto userDto) {
        return new UserEntity(
                userDto.id(),
                userDto.name(),
                userDto.email()
        );
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        return new UserDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }
}

package music.kmmk.backend.data.user.mapper;

import music.kmmk.backend.core.user.model.User;
import music.kmmk.backend.data.mapper.EntityModelMapper;
import music.kmmk.backend.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityModelMapper implements EntityModelMapper<UserEntity, User> {

    @Override
    public UserEntity modelToEntity(User userDto) {
        return new UserEntity(
                userDto.id(),
                userDto.name(),
                userDto.email()
        );
    }

    @Override
    public User entityToModel(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }
}

package music.kmmk.backend.data.album.mapper;

import music.kmmk.backend.data.user.mapper.UserEntityModelMapper;
import music.kmmk.backend.core.album.model.UserRating;
import music.kmmk.backend.data.album.entity.UserRatingEntity;
import music.kmmk.backend.data.mapper.EntityModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRatingEntityModelMapper implements EntityModelMapper<UserRatingEntity, UserRating> {

    private final UserEntityModelMapper userEntityModelMapper;

    public UserRatingEntityModelMapper(UserEntityModelMapper userEntityModelMapper) {
        this.userEntityModelMapper = userEntityModelMapper;
    }

    @Override
    public UserRatingEntity modelToEntity(UserRating userRating) {
        return new UserRatingEntity(
                userEntityModelMapper.modelToEntity(userRating.user()),
                userRating.rating()
        );
    }

    @Override
    public UserRating entityToModel(UserRatingEntity userRatingEntity) {
        return new UserRating(
                userEntityModelMapper.entityToModel(userRatingEntity.getUser()),
                userRatingEntity.getRating()
        );
    }
}

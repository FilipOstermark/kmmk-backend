package music.kmmk.backend.api.album.mapper;

import music.kmmk.backend.api.album.dto.UserRatingDto;
import music.kmmk.backend.api.mapper.ModelDtoMapper;
import music.kmmk.backend.api.user.mapper.UserModelDtoMapper;
import music.kmmk.backend.core.album.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRatingModelDtoMapper implements ModelDtoMapper<UserRating, UserRatingDto> {

    private final UserModelDtoMapper userModelDtoMapper;


    @Autowired
    public UserRatingModelDtoMapper(UserModelDtoMapper userModelDtoMapper) {
        this.userModelDtoMapper = userModelDtoMapper;
    }

    @Override
    public UserRatingDto modelToDto(UserRating userRating) {
        return new UserRatingDto(
                userModelDtoMapper.modelToDto(userRating.user()),
                userRating.rating()
        );
    }

    @Override
    public UserRating dtoToModel(UserRatingDto userRatingDto) {
        return new UserRating(
                userModelDtoMapper.dtoToModel(userRatingDto.user()),
                userRatingDto.rating()
        );
    }
}

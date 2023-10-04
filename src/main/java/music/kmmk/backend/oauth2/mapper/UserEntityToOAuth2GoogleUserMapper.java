package music.kmmk.backend.oauth2.mapper;

import music.kmmk.backend.common.mapper.EntityDtoMapper;
import music.kmmk.backend.oauth2.model.GoogleOAuth2User;
import music.kmmk.backend.user.data.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToOAuth2GoogleUserMapper implements EntityDtoMapper<UserEntity, GoogleOAuth2User> {

    @Override
    public UserEntity toEntity(GoogleOAuth2User GoogleOAuth2User) {
        return new UserEntity(
                GoogleOAuth2User.getName(),
                GoogleOAuth2User.getEmail()
        );
    }

    @Override
    public GoogleOAuth2User toDto(UserEntity userEntity) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

}

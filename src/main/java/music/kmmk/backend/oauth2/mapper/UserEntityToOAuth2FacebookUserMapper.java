package music.kmmk.backend.oauth2.mapper;

import music.kmmk.backend.common.mapper.EntityDtoMapper;
import music.kmmk.backend.oauth2.model.FacebookOAuth2User;
import music.kmmk.backend.user.data.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToOAuth2FacebookUserMapper implements EntityDtoMapper<UserEntity, FacebookOAuth2User> {

    @Override
    public UserEntity toEntity(FacebookOAuth2User facebookOAuth2User) {
        return new UserEntity(
                facebookOAuth2User.getName(),
                facebookOAuth2User.getEmail()
        );
    }

    @Override
    public FacebookOAuth2User toDto(UserEntity userEntity) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported");
    }

}

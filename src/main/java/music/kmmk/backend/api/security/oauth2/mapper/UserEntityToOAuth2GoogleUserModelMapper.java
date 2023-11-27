package music.kmmk.backend.api.security.oauth2.mapper;

import music.kmmk.backend.data.mapper.EntityModelMapper;
import music.kmmk.backend.api.security.oauth2.model.GoogleOAuth2User;
import music.kmmk.backend.data.user.entity.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class UserEntityToOAuth2GoogleUserModelMapper implements EntityModelMapper<UserEntity, GoogleOAuth2User> {

    @Override
    public UserEntity modelToEntity(GoogleOAuth2User GoogleOAuth2User) {
        return new UserEntity(
                GoogleOAuth2User.getName(),
                GoogleOAuth2User.getEmail()
        );
    }

    @Override
    public GoogleOAuth2User entityToModel(UserEntity userEntity) throws UnsupportedOperationException {
        return new GoogleOAuth2User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                Map.of()
        );
    }

}

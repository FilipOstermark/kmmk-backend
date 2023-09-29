package music.kmmk.backend.oauth2.service;

import music.kmmk.backend.oauth2.mapper.UserEntityToOAuth2FacebookUserMapper;
import music.kmmk.backend.oauth2.model.FacebookOAuth2User;
import music.kmmk.backend.user.data.UserEntity;
import music.kmmk.backend.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * Reference:
 * <a href="https://www.codejava.net/frameworks/spring-boot/social-login-with-facebook-example">
 *     CodeJava - Login with Facebook
 * </a>
 */
@Service
public class FacebookOAuth2UserService extends DefaultOAuth2UserService  {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserEntityToOAuth2FacebookUserMapper userMapper;

    @Autowired
    public FacebookOAuth2UserService(UserRepository userRepository, UserEntityToOAuth2FacebookUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final OAuth2User user = super.loadUser(userRequest);
        return new FacebookOAuth2User(user);
    }

    public void saveUser(FacebookOAuth2User facebookOAuth2User) {
        final UserEntity userEntity = this.userMapper.toEntity(facebookOAuth2User);
        this.userRepository.saveIfNotExists(userEntity);
    }

}

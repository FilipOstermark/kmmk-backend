package music.kmmk.backend.oauth2.service;

import music.kmmk.backend.common.util.StringExtensions;
import music.kmmk.backend.oauth2.mapper.UserEntityToOAuth2GoogleUserMapper;
import music.kmmk.backend.oauth2.model.GoogleOAuth2User;
import music.kmmk.backend.user.data.UserEntity;
import music.kmmk.backend.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class OAuth2UserServiceGoogleImpl extends DefaultOAuth2UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserEntityToOAuth2GoogleUserMapper userMapper;

    @Autowired
    public OAuth2UserServiceGoogleImpl(UserRepository userRepository, UserEntityToOAuth2GoogleUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final OAuth2User oAuth2User = super.loadUser(userRequest);
        final String email = oAuth2User.getAttribute("name");
        if (StringExtensions.isNullOrBlank(email)) {
            throw new OAuth2AuthenticationException("Failed to get e-mail from user");
        }

        final GoogleOAuth2User googleOAuth2User = new GoogleOAuth2User(oAuth2User);
        this.saveUserIfNotExists(googleOAuth2User);

        return googleOAuth2User;
    }

    public void saveUserIfNotExists(GoogleOAuth2User googleOAuth2User) {
        final UserEntity userEntity = this.userMapper.toEntity(googleOAuth2User);
        this.userRepository.saveIfNotExists(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserEntity user = this.userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email '" + email +"' was not found")
                );

        return this.userMapper.toDto(user);
    }

}

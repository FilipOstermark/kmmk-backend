package music.kmmk.backend.api.security.oauth2.service;

import music.kmmk.backend.api.security.oauth2.mapper.UserEntityToOAuth2GoogleUserModelMapper;
import music.kmmk.backend.core.util.StringExtensions;
import music.kmmk.backend.api.security.oauth2.model.GoogleOAuth2User;
import music.kmmk.backend.data.user.entity.UserEntity;
import music.kmmk.backend.data.user.repository.UserSpringCrudDataSource;
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
    private final UserSpringCrudDataSource userRepository;

    @Autowired
    private final UserEntityToOAuth2GoogleUserModelMapper userMapper;

    @Autowired
    public OAuth2UserServiceGoogleImpl(UserSpringCrudDataSource userRepository, UserEntityToOAuth2GoogleUserModelMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final OAuth2User oAuth2User = super.loadUser(userRequest);
        final String email = oAuth2User.getAttribute("email");
        if (StringExtensions.isNullOrBlank(email)) {
            throw new OAuth2AuthenticationException("Failed to get e-mail from user");
        }

        // TODO Factory
        final UserEntity newUser = new UserEntity(oAuth2User.getAttribute("name"),
                oAuth2User.getAttribute("email"));
        final UserEntity existingUser = this.userRepository.saveIfNotExists(newUser);
        return new GoogleOAuth2User(
                existingUser.getId(),
                existingUser.getName(),
                existingUser.getEmail(),
                null,
                oAuth2User.getAuthorities(),
                oAuth2User.getAttributes()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserEntity user = this.userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email '" + email +"' was not found")
                );

        return this.userMapper.entityToModel(user);
    }

}

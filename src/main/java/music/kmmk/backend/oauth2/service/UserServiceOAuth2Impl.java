package music.kmmk.backend.oauth2.service;

import music.kmmk.backend.oauth2.model.FacebookOAuth2User;
import music.kmmk.backend.user.data.UserEntity;
import music.kmmk.backend.user.data.UserRepository;
import music.kmmk.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceOAuth2Impl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceOAuth2Impl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> getAuthenticatedUser() {

        Authentication authn = SecurityContextHolder.getContext().getAuthentication();
        if (!authn.isAuthenticated()) {
            return Optional.empty();
        }

        final String email = ((FacebookOAuth2User) authn.getPrincipal()).getEmail();

        return this.userRepository.findByEmail(email);
    }
}

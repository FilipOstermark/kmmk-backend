package music.kmmk.backend.data.user.repository;

import music.kmmk.backend.api.security.oauth2.model.GoogleOAuth2User;
import music.kmmk.backend.core.user.model.User;
import music.kmmk.backend.core.user.repository.AuthenticationRepository;
import music.kmmk.backend.core.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthenticationRepositoryImpl implements AuthenticationRepository {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return Optional.empty();
        }

        // TODO Fix dependency on GoogleOAuth2User - Implement own authentication class
        final String email = ((GoogleOAuth2User) authentication.getPrincipal()).getEmail();

        return this.userRepository.getUserByEmail(email);
    }
}

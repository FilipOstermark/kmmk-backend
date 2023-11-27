package music.kmmk.backend.core.user.repository;

import music.kmmk.backend.core.user.model.User;

import java.util.Optional;

public interface AuthenticationRepository {
    Optional<User> getAuthenticatedUser();
}

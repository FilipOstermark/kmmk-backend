package music.kmmk.backend.core.user.service;

import music.kmmk.backend.core.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getAuthenticatedUser();

    Optional<User> deleteAuthenticatedUser();

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();
}

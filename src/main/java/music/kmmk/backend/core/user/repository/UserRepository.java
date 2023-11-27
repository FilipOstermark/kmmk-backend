package music.kmmk.backend.core.user.repository;

import music.kmmk.backend.core.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> deleteUser(User authenticatedUser);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    Optional<User> getUserByEmail(String email);
}

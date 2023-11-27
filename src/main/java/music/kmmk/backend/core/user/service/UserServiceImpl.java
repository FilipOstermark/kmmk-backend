package music.kmmk.backend.core.user.service;

import music.kmmk.backend.core.user.model.User;
import music.kmmk.backend.core.user.repository.AuthenticationRepository;
import music.kmmk.backend.core.user.repository.UserRepository;
import music.kmmk.backend.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            AuthenticationRepository authenticationRepository
    ) {
        this.userRepository = userRepository;
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public Optional<User> getAuthenticatedUser() {
        return authenticationRepository.getAuthenticatedUser();
    }

    @Override
    public Optional<User> deleteAuthenticatedUser() {
        Optional<User> authenticatedUser = authenticationRepository.getAuthenticatedUser();
        if (authenticatedUser.isEmpty()) {
            return Optional.empty();
        }

        return userRepository.deleteUser(authenticatedUser.get());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

}

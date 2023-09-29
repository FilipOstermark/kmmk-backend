package music.kmmk.backend.user.service;

import music.kmmk.backend.user.data.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> getAuthenticatedUser();

}

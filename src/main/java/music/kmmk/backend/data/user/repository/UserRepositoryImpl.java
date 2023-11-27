package music.kmmk.backend.data.user.repository;

import music.kmmk.backend.data.user.mapper.UserEntityModelMapper;
import music.kmmk.backend.core.user.model.User;
import music.kmmk.backend.core.user.repository.UserRepository;
import music.kmmk.backend.data.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserSpringCrudDataSource userSpringCrudDataSource;
    private final UserEntityModelMapper userEntityModelMapper;

    @Autowired
    public UserRepositoryImpl(
            UserSpringCrudDataSource userSpringCrudDataSource,
            UserEntityModelMapper userEntityModelMapper
    ) {
        this.userSpringCrudDataSource = userSpringCrudDataSource;
        this.userEntityModelMapper = userEntityModelMapper;
    }

    @Override
    public Optional<User> deleteUser(User authenticatedUser) {
        Optional<UserEntity> existingUserEntityOptional = userSpringCrudDataSource.findById(
                authenticatedUser.id()
        );
        if (existingUserEntityOptional.isEmpty()) {
            return Optional.empty();
        }

        UserEntity userEntity = existingUserEntityOptional.get();
        userSpringCrudDataSource.delete(userEntity);
        return Optional.of(userEntityModelMapper.entityToModel(userEntity));
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userSpringCrudDataSource.findById(id);
        return userEntityOptional.map(userEntityModelMapper::entityToModel);
    }

    @Override
    public List<User> getAllUsers() {
        return userEntityModelMapper.entitiesToModels(userSpringCrudDataSource.findAll());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userSpringCrudDataSource.findByEmail(email).map(userEntityModelMapper::entityToModel);
    }
}

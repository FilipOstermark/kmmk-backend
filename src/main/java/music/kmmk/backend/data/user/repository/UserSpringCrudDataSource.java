package music.kmmk.backend.data.user.repository;

import jakarta.transaction.Transactional;
import music.kmmk.backend.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserSpringCrudDataSource extends
        CrudRepository<UserEntity, Long>,
        PagingAndSortingRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM users u WHERE u.email=:email", nativeQuery = true)
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Transactional
    default UserEntity saveIfNotExists(UserEntity user) {
        return this
                .findByEmail(user.getEmail())
                .orElseGet(() -> this.save(user));
    }
}

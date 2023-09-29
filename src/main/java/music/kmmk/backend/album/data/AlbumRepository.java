package music.kmmk.backend.album.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends
        CrudRepository<AlbumEntity, Long>,
        PagingAndSortingRepository<AlbumEntity, Long> {
}

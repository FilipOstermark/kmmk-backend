package music.kmmk.backend.data.album.repository;

import music.kmmk.backend.data.album.entity.AlbumEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumSpringDataSource extends
        CrudRepository<AlbumEntity, Long>,
        PagingAndSortingRepository<AlbumEntity, Long> {
}

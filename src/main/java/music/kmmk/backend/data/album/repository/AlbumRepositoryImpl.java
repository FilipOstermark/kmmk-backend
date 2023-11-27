package music.kmmk.backend.data.album.repository;

import music.kmmk.backend.core.album.model.Album;
import music.kmmk.backend.core.album.repository.AlbumRepository;
import music.kmmk.backend.data.album.entity.AlbumEntity;
import music.kmmk.backend.data.album.mapper.AlbumEntityModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumRepositoryImpl implements AlbumRepository {

    private final AlbumSpringDataSource albumSpringDataSource;
    private final AlbumEntityModelMapper albumEntityModelMapper;

    @Autowired
    public AlbumRepositoryImpl(
            AlbumSpringDataSource albumSpringDataSource,
            AlbumEntityModelMapper albumEntityModelMapper
    ) {
        this.albumSpringDataSource = albumSpringDataSource;
        this.albumEntityModelMapper = albumEntityModelMapper;
    }

    @Override
    public List<Album> getAll() {
        return albumEntityModelMapper.entitiesToModels(albumSpringDataSource.findAll());
    }

    @Override
    public Album save(Album album) {
        AlbumEntity insertEntity = albumEntityModelMapper.modelToEntity(album);
        AlbumEntity createdEntity = albumSpringDataSource.save(insertEntity);
        return albumEntityModelMapper.entityToModel(createdEntity);
    }
}

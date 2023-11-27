package music.kmmk.backend.core.album.service;

import music.kmmk.backend.core.album.model.Album;
import music.kmmk.backend.core.album.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAll() {
        return albumRepository.getAll();
    }

    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }
}

package music.kmmk.backend.core.album.repository;

import music.kmmk.backend.api.album.dto.AlbumDto;
import music.kmmk.backend.core.album.model.Album;

import java.util.List;

public interface AlbumRepository {
    List<Album> getAll();

    Album save(Album album);
}

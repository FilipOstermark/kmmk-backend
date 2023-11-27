package music.kmmk.backend.core.album.service;

import music.kmmk.backend.core.album.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAll();

    Album save(Album album);
}

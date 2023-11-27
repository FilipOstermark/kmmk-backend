package music.kmmk.backend.core.album.service;

import music.kmmk.backend.data.musicbrainz.dto.ReleaseGroupsDto;

import java.net.URISyntaxException;

public interface AlbumSearchService {

    ReleaseGroupsDto search(String query) throws URISyntaxException;

}

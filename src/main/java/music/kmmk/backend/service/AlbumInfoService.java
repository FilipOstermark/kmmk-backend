package music.kmmk.backend.service;

import music.kmmk.backend.dto.ReleaseGroupsDto;

import java.net.URISyntaxException;

public interface AlbumInfoService {

    ReleaseGroupsDto search(String query) throws URISyntaxException;

}

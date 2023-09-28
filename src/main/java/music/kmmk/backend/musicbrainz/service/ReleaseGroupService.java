package music.kmmk.backend.musicbrainz.service;

import music.kmmk.backend.musicbrainz.dto.ReleaseGroupsDto;

import java.net.URISyntaxException;

public interface ReleaseGroupService {

    ReleaseGroupsDto search(String query) throws URISyntaxException;

}

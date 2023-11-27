package music.kmmk.backend.api.album.controller;

import music.kmmk.backend.api.Constants;
import music.kmmk.backend.data.musicbrainz.dto.ReleaseGroupsDto;
import music.kmmk.backend.core.album.service.AlbumSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(Constants.API_V1_URI + "/release-group")
public class ReleaseGroupController {

    private final AlbumSearchService albumSearchService;

    @Autowired
    public ReleaseGroupController(AlbumSearchService albumSearchService) {
        this.albumSearchService = albumSearchService;
    }

    @GetMapping
    public ReleaseGroupsDto searchReleaseGroup(@RequestParam String query) throws URISyntaxException {
        return this.albumSearchService.search(query);
    }

}

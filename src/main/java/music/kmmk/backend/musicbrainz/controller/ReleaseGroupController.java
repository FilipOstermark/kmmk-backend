package music.kmmk.backend.musicbrainz.controller;

import music.kmmk.backend.common.Constants;
import music.kmmk.backend.musicbrainz.dto.ReleaseGroupsDto;
import music.kmmk.backend.musicbrainz.service.ReleaseGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(Constants.API_V1_URI + "/release-group")
public class ReleaseGroupController {

    private final ReleaseGroupService releaseGroupService;

    @Autowired
    public ReleaseGroupController(ReleaseGroupService releaseGroupService) {
        this.releaseGroupService = releaseGroupService;
    }

    @GetMapping
    public ReleaseGroupsDto searchReleaseGroup(@RequestParam String query) throws URISyntaxException {
        return this.releaseGroupService.search(query);
    }

}

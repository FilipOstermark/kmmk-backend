package music.kmmk.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import music.kmmk.backend.dto.ReleaseGroupsDto;
import music.kmmk.backend.service.AlbumInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.function.Function;


@RestController
@RequestMapping("album-info")
public class AlbumInfoController {

    private final AlbumInfoService albumInfoService;

    @Autowired
    public AlbumInfoController(AlbumInfoService albumInfoService) {
        this.albumInfoService = albumInfoService;
    }

    @RequestMapping("/release-group")
    public ReleaseGroupsDto searchReleaseGroup(@RequestParam String query) throws URISyntaxException {
        return this.albumInfoService.search(query);
    }

}

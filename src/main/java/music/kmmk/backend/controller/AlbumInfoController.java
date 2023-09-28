package music.kmmk.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import music.kmmk.backend.data.AlbumEntity;
import music.kmmk.backend.data.AlbumRepository;
import music.kmmk.backend.dto.AlbumDto;
import music.kmmk.backend.dto.ReleaseGroupsDto;
import music.kmmk.backend.service.AlbumInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("album-info")
public class AlbumInfoController {

    // TODO Move this dep. to service?
    private final AlbumRepository albumRepository;

    private final AlbumInfoService albumInfoService;

    @Autowired
    public AlbumInfoController(AlbumInfoService albumInfoService, AlbumRepository albumRepository) {
        this.albumInfoService = albumInfoService;
        this.albumRepository = albumRepository;
    }

    @CrossOrigin
    @RequestMapping("/release-group")
    public ReleaseGroupsDto searchReleaseGroup(@RequestParam String query) throws URISyntaxException {
        return this.albumInfoService.search(query);
    }

    @PostMapping("/create")
    public AlbumEntity createAlbum(@RequestBody AlbumDto album) {

        final AlbumEntity entity = new AlbumEntity(
                album.mbid(),
                album.title(),
                album.artistName(),
                album.releaseYear(),
                album.summary(),
                album.bestSongTitle(),
                album.worstSongTitle(),
                album.listeningOccasion(),
                album.discussionDate());

        return this.albumRepository.save(entity);
    }

    @GetMapping("/albums")
    public List<AlbumDto> findAllAlbums() {
        return StreamSupport.stream(albumRepository.findAll().spliterator(), false)
                .map(entity -> new AlbumDto(
                        entity.getMbid(),
                        entity.getTitle(),
                        entity.getArtistName(),
                        entity.getReleaseYear(),
                        entity.getSummary(),
                        entity.getBestSongTitle(),
                        entity.getWorstSongTitle(),
                        entity.getListeningOccasion(),
                        entity.getDiscussionDate()
                )).toList();
    }

}

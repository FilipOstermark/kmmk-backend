package music.kmmk.backend.album.controller;

import music.kmmk.backend.album.data.AlbumEntity;
import music.kmmk.backend.album.data.AlbumRepository;
import music.kmmk.backend.album.data.UserRatingEntity;
import music.kmmk.backend.album.dto.AlbumDto;
import music.kmmk.backend.album.dto.UserRatingDto;
import music.kmmk.backend.album.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("album")
public class AlbumController {

    // TODO Move this dep. to service?
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumController(AlbumRepository albumRepository, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
    }



    @PostMapping("/")
    public AlbumEntity createAlbum(@RequestBody AlbumDto albumDto) {
        final AlbumEntity albumEntity = albumMapper.toEntity(albumDto);
        return this.albumRepository.save(albumEntity);
    }

    @GetMapping("/list")
    public List<AlbumDto> findAllAlbums() {
        // TODO Paginate query
        return albumMapper.toDtoList(albumRepository.findAll());
    }

}

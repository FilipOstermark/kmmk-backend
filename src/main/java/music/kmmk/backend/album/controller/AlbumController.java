package music.kmmk.backend.album.controller;

import music.kmmk.backend.album.data.AlbumEntity;
import music.kmmk.backend.album.data.AlbumRepository;
import music.kmmk.backend.album.dto.AlbumDto;
import music.kmmk.backend.album.dto.AlbumListDto;
import music.kmmk.backend.album.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping
    public AlbumEntity create(@RequestBody AlbumDto albumDto) {
        final AlbumEntity albumEntity = albumMapper.toEntity(albumDto);
        return this.albumRepository.save(albumEntity);
    }

    @GetMapping("/list")
    public AlbumListDto readList() {
        final List<AlbumDto> albums = albumMapper.toDtoList(
                albumRepository.findAll()
        );

        // TODO Proper pagination
        return new AlbumListDto(albums.size(), 0, 0, albums);
    }

}

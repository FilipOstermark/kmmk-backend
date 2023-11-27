package music.kmmk.backend.api.album.controller;

import music.kmmk.backend.api.album.mapper.AlbumModelDtoMapper;
import music.kmmk.backend.core.album.model.Album;
import music.kmmk.backend.core.album.service.AlbumService;
import music.kmmk.backend.api.album.dto.AlbumDto;
import music.kmmk.backend.api.album.dto.AlbumListDto;
import music.kmmk.backend.api.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(Constants.API_V1_URI + "/album")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumModelDtoMapper albumMapper;

    @Autowired
    public AlbumController(AlbumService albumService, AlbumModelDtoMapper albumMapper) {
        this.albumService = albumService;
        this.albumMapper = albumMapper;
    }

    @PostMapping
    public AlbumDto create(@RequestBody AlbumDto albumDto) {
        final Album insertAlbum = albumMapper.dtoToModel(albumDto);
        final Album createdAlbum = albumService.save(insertAlbum);
        return albumMapper.modelToDto(createdAlbum);
    }

    @GetMapping("/list")
    public AlbumListDto readList() {
        final List<AlbumDto> albums = albumMapper.modelsToDtos(albumService.getAll());
        // TODO Proper pagination
        return new AlbumListDto(albums.size(), 0, 0, albums);
    }

}

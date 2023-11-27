package music.kmmk.backend.api.album.mapper;

import music.kmmk.backend.api.album.dto.AlbumDto;
import music.kmmk.backend.api.mapper.ModelDtoMapper;
import music.kmmk.backend.api.user.mapper.UserModelDtoMapper;
import music.kmmk.backend.core.album.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlbumModelDtoMapper implements ModelDtoMapper<Album, AlbumDto> {

    private final UserRatingModelDtoMapper userRatingModelDtoMapper;
    private final UserModelDtoMapper userModelDtoMapper;

    @Autowired
    public AlbumModelDtoMapper(
            UserRatingModelDtoMapper userRatingModelDtoMapper,
            UserModelDtoMapper userModelDtoMapper
    ) {
        this.userRatingModelDtoMapper = userRatingModelDtoMapper;
        this.userModelDtoMapper = userModelDtoMapper;
    }

    @Override
    public AlbumDto modelToDto(Album album) {
        return new AlbumDto(
                album.id(),
                album.mbid(),
                album.title(),
                album.artistName(),
                album.releaseYear(),
                album.summary(),
                album.bestSongTitle(),
                album.worstSongTitle(),
                album.listeningOccasion(),
                album.discussionDate(),
                userRatingModelDtoMapper.modelsToDtos(album.ratings()),
                userModelDtoMapper.modelToDto(album.pickedByUser()),
                album.coverArtUrl()
        );
    }

    @Override
    public Album dtoToModel(AlbumDto albumDto) {
        return new Album(
                albumDto.id(),
                albumDto.mbid(),
                albumDto.title(),
                albumDto.artistName(),
                albumDto.releaseYear(),
                albumDto.summary(),
                albumDto.bestSongTitle(),
                albumDto.worstSongTitle(),
                albumDto.listeningOccasion(),
                albumDto.discussionDate(),
                userRatingModelDtoMapper.dtosToModels(albumDto.ratings()),
                userModelDtoMapper.dtoToModel(albumDto.pickedBy()),
                albumDto.coverArtUrl()
        );
    }
}

package music.kmmk.backend.album.mapper;

import music.kmmk.backend.album.data.AlbumEntity;
import music.kmmk.backend.album.data.UserRatingEntity;
import music.kmmk.backend.album.dto.AlbumDto;
import music.kmmk.backend.album.dto.UserRatingDto;
import music.kmmk.backend.common.mapper.EntityDtoMapper;
import music.kmmk.backend.user.mapper.UserEntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AlbumMapper implements EntityDtoMapper<AlbumEntity, AlbumDto> {

    private final UserEntityDtoMapper userMapper;

    @Autowired
    public AlbumMapper(UserEntityDtoMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public AlbumEntity toEntity(AlbumDto dto) {
        final Set<UserRatingEntity> ratings = dto.ratings()
                .stream()
                .map(this::userRatingDtoToEntity)
                .collect(Collectors.toSet());

        final AlbumEntity album = new AlbumEntity(
                dto.mbid(),
                dto.title(),
                dto.artistName(),
                dto.releaseYear(),
                dto.summary(),
                dto.bestSongTitle(),
                dto.worstSongTitle(),
                dto.listeningOccasion(),
                dto.discussionDate(),
                ratings);

        ratings.forEach(rating -> rating.setAlbum(album));

        return album;
    }

    @Override
    public AlbumDto toDto(AlbumEntity albumEntity) {
        return new AlbumDto(
                albumEntity.getId(),
                albumEntity.getMbid(),
                albumEntity.getTitle(),
                albumEntity.getArtistName(),
                albumEntity.getReleaseYear(),
                albumEntity.getSummary(),
                albumEntity.getBestSongTitle(),
                albumEntity.getWorstSongTitle(),
                albumEntity.getListeningOccasion(),
                albumEntity.getDiscussionDate(),
                albumEntity.getRatings().stream().map(this::userRatingEntityToDto).toList()
        );
    }

    private UserRatingEntity userRatingDtoToEntity(UserRatingDto dto) {

        return new UserRatingEntity(
                this.userMapper.toEntity(dto.user()),
                dto.rating()
        );
    }

    private UserRatingDto userRatingEntityToDto(UserRatingEntity entity) {
        return new UserRatingDto(
                this.userMapper.toDto(entity.getUser()),
                entity.getRating()
        );
    }
}

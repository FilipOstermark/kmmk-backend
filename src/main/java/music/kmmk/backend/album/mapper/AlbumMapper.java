package music.kmmk.backend.album.mapper;

import music.kmmk.backend.album.data.AlbumEntity;
import music.kmmk.backend.album.data.UserRatingEntity;
import music.kmmk.backend.album.dto.AlbumDto;
import music.kmmk.backend.album.dto.UserRatingDto;
import music.kmmk.backend.common.mapper.EntityDtoMapper;

import java.util.Set;
import java.util.stream.Collectors;

public class AlbumMapper implements EntityDtoMapper<AlbumEntity, AlbumDto> {
    @Override
    public AlbumEntity toEntity(AlbumDto dto) {
        final Set<UserRatingEntity> ratings = dto.ratings()
                .stream()
                .map(this::userRatingDtoToEntity)
                .collect(Collectors.toSet());

        return new AlbumEntity(
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
                dto.userEmail(),
                dto.rating()
        );
    }

    private UserRatingDto userRatingEntityToDto(UserRatingEntity entity) {
        return new UserRatingDto(
                entity.getUserEmail(),
                entity.getRating()
        );
    }
}

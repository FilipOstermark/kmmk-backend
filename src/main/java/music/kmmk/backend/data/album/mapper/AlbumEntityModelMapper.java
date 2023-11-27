package music.kmmk.backend.data.album.mapper;

import music.kmmk.backend.data.user.mapper.UserEntityModelMapper;
import music.kmmk.backend.core.album.model.Album;
import music.kmmk.backend.data.album.entity.AlbumEntity;
import music.kmmk.backend.data.mapper.EntityModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class AlbumEntityModelMapper implements EntityModelMapper<AlbumEntity, Album> {

    private final UserEntityModelMapper userMapper;
    private final UserRatingEntityModelMapper userRatingEntityModelMapper;

    @Autowired
    public AlbumEntityModelMapper(
            UserEntityModelMapper userMapper,
            UserRatingEntityModelMapper userRatingEntityModelMapper
    ) {
        this.userMapper = userMapper;
        this.userRatingEntityModelMapper = userRatingEntityModelMapper;
    }

    @Override
    public AlbumEntity modelToEntity(Album model) {
        final AlbumEntity album = new AlbumEntity(
                model.mbid(),
                model.title(),
                model.artistName(),
                model.releaseYear(),
                model.summary(),
                model.bestSongTitle(),
                model.worstSongTitle(),
                model.listeningOccasion(),
                model.discussionDate(),
                new HashSet<>(userRatingEntityModelMapper.modelsToEntities(model.ratings())),
                this.userMapper.modelToEntity(model.pickedByUser()),
                model.coverArtUrl());

        album.getRatings().forEach(rating -> rating.setAlbum(album));

        return album;
    }

    @Override
    public Album entityToModel(AlbumEntity albumEntity) {
        return new Album(
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
                userRatingEntityModelMapper.entitiesToModels(albumEntity.getRatings()),
                this.userMapper.entityToModel(albumEntity.getPickedByUser()),
                albumEntity.getCoverArtUrl()
        );
    }
}

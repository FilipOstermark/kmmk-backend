package music.kmmk.backend.core.album.model;

import music.kmmk.backend.core.user.model.User;

import java.util.List;

public record Album(
        Long id,
        String mbid,
        String title,
        String artistName,
        Integer releaseYear,
        String summary,
        String bestSongTitle,
        String worstSongTitle,
        String listeningOccasion,
        String discussionDate,
        List<UserRating> ratings,
        User pickedByUser,
        String coverArtUrl
) { }

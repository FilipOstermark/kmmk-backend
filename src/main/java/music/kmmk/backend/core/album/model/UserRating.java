package music.kmmk.backend.core.album.model;

import music.kmmk.backend.core.user.model.User;

public record UserRating(
        User user,
        int rating
) { }
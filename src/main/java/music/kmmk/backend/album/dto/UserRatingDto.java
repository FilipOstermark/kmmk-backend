package music.kmmk.backend.album.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRatingDto(
        @JsonProperty("userEmail") String userEmail,
        @JsonProperty("rating") Integer rating
) {
}

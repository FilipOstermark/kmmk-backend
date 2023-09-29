package music.kmmk.backend.album.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import music.kmmk.backend.user.dto.UserDto;

public record UserRatingDto(
        @JsonProperty("userEmail") UserDto user,
        @JsonProperty("rating") Integer rating
) {
}

package music.kmmk.backend.api.album.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import music.kmmk.backend.api.user.dto.UserDto;

public record UserRatingDto(
        @JsonProperty("user") UserDto user,
        @JsonProperty("rating") Integer rating
) {
}

package music.kmmk.backend.api.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO Add URI
public record UserDto(
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("email") String email
) {
}

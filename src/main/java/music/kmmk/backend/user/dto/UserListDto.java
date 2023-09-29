package music.kmmk.backend.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UserListDto(
        @JsonProperty("count") int count,
        @JsonProperty("page") int page,
        @JsonProperty("lastPage") int lastPage,
        @JsonProperty("users") List<UserDto> users
) {
}

package music.kmmk.backend.album.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import music.kmmk.backend.user.dto.UserDto;

import java.util.List;

// TODO Add URI
@JsonIgnoreProperties(ignoreUnknown = true)
public record AlbumDto(
    @JsonProperty("id") Long id,
    @JsonProperty("mbid") String mbid,
    @JsonProperty("title") String title,
    @JsonProperty("artistName") String artistName,
    @JsonProperty("releaseYear") int releaseYear,
    @JsonProperty("summary") String summary,
    @JsonProperty("bestSongTitle") String bestSongTitle,
    @JsonProperty("worstSongTitle") String worstSongTitle,
    @JsonProperty("listeningOccasion") String listeningOccasion,
    @JsonProperty("discussionDate") String discussionDate,
    @JsonProperty("ratings") List<UserRatingDto> ratings,
    @JsonProperty("pickedBy") UserDto pickedBy
) {
}

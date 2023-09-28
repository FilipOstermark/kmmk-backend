package music.kmmk.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AlbumDto(
    @JsonProperty("mbid") String mbid,
    @JsonProperty("title") String title,
    @JsonProperty("artistName") String artistName,
    @JsonProperty("releaseYear") int releaseYear,
    @JsonProperty("summary") String summary,
    @JsonProperty("bestSongTitle") String bestSongTitle,
    @JsonProperty("worstSongTitle") String worstSongTitle,
    @JsonProperty("listeningOccasion") String listeningOccasion,
    @JsonProperty("discussionDate") String discussionDate
) {
}

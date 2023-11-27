package music.kmmk.backend.data.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ArtistCreditDto(
        @JsonProperty("name") String name,
        @JsonProperty("artist") ArtistDto artist
) { }

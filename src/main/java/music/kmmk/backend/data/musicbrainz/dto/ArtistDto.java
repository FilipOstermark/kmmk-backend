package music.kmmk.backend.data.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ArtistDto(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("sort-name") String sortName
) { }

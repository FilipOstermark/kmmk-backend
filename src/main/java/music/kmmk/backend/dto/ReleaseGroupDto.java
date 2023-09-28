package music.kmmk.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReleaseGroupDto(
        @JsonProperty("id") String id,
        @JsonProperty("type-id") String typeId,
        @JsonProperty("score") int score,
        @JsonProperty("primary-type-id") String primaryTypeId,
        @JsonProperty("primary-type") String primaryType,
        @JsonProperty("count") int count,
        @JsonProperty("title") String title,
        @JsonProperty("first-release-date") String firstReleaseDate,
        @JsonProperty("artist-credit") List<ArtistCreditDto> artistCredit

) { }

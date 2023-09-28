package music.kmmk.backend.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReleaseGroupsDto(
        @JsonProperty("created") String created,
        @JsonProperty("count") int count,
        @JsonProperty("offset") int offset,
        @JsonProperty("release-groups") List<ReleaseGroupDto> releaseGroups
) { }

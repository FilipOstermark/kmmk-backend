package music.kmmk.backend.data.musicbrainz.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import music.kmmk.backend.data.musicbrainz.dto.ReleaseGroupsDto;
import org.springframework.stereotype.Component;

@Component
public class ReleaseGroupMapper {

    private final ObjectMapper objectMapper;

    public ReleaseGroupMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ReleaseGroupsDto jsonToReleaseGroupsDto(String jsonStr) {
        try {
            return this.objectMapper.readValue(jsonStr, ReleaseGroupsDto.class);
        } catch (JsonProcessingException e) {
            // TODO Custom exception
            throw new IllegalStateException(e);
        }
    }
}

package music.kmmk.backend.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import music.kmmk.backend.dto.ReleaseGroupsDto;
import org.springframework.stereotype.Component;

@Component
public class AlbumInfoMapper {

    private final ObjectMapper objectMapper;

    public AlbumInfoMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ReleaseGroupsDto jsonToReleaseGroupDto(String jsonStr) {
        try {
            return this.objectMapper.readValue(jsonStr, ReleaseGroupsDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

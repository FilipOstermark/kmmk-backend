package music.kmmk.backend.common.controller;

import music.kmmk.backend.common.Constants;
import music.kmmk.backend.common.dto.MessageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_V1_URI + "/health")
public class HealthController {

    @GetMapping
    public MessageDto healthCheck() {
        return new MessageDto();
    }

}

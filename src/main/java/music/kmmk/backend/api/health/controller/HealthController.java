package music.kmmk.backend.api.health.controller;

import music.kmmk.backend.api.Constants;
import music.kmmk.backend.api.dto.MessageDto;
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

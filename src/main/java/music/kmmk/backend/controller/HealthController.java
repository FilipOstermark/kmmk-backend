package music.kmmk.backend.controller;

import music.kmmk.backend.dto.MessageDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @RequestMapping("/")
    public MessageDto healthCheck() {
        return new MessageDto();
    }

}

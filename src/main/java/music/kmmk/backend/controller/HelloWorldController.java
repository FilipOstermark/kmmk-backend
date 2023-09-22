package music.kmmk.backend.controller;

import music.kmmk.backend.model.HelloWorld;
import music.kmmk.backend.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    private final HelloWorldService helloWorldService;

    @Autowired
    public HelloWorldController(@Qualifier("HelloWorldServiceImpl") HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @RequestMapping("/yo")
    public HelloWorld helloWorld() {
        return helloWorldService.getHello();
    }
}

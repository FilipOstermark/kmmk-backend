package music.kmmk.backend.service;

import music.kmmk.backend.data.HelloWorldRepository;
import music.kmmk.backend.model.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component("HelloWorldServiceImpl")
public class HelloWorldServiceImpl implements HelloWorldService {

    private final HelloWorldRepository helloWorldRepository;

    @Autowired
    public HelloWorldServiceImpl(HelloWorldRepository helloWorldRepository) {
        this.helloWorldRepository = helloWorldRepository;
    }

    private static final Predicate<String> removeIfEqualsMy = value -> !value.equals("my");
    private static final Function<String, String> addExclamationMarks = value -> value + "!!";

    @Override
    public HelloWorld getHello() {
        List<String> hello = List.of("Yo", "my", "man");
        final String message = hello.stream()
                .filter(removeIfEqualsMy)
                .map(addExclamationMarks)
                .collect(Collectors.joining(", "));

        return this.helloWorldRepository
                .getAll()
                .stream()
                .findFirst()
                .orElse(
                        new HelloWorld(message, "Some extra data to test POJO -> JSON mapping.")
                );
    }
}

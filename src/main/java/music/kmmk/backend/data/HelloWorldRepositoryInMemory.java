package music.kmmk.backend.data;

import music.kmmk.backend.model.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("HelloWorldRepositoryInMemory")
public class HelloWorldRepositoryInMemory implements HelloWorldRepository {

    private final List<HelloWorld> helloWorldMessages;

    @Autowired
    public HelloWorldRepositoryInMemory() {
        synchronized (this) {
            this.helloWorldMessages = new ArrayList<>(List.of(new HelloWorld("Yo!", "Extra data")));
        }
    }

    @Override
    public List<HelloWorld> getAll() {
        synchronized (this) {
            return this.helloWorldMessages;
        }
    }

    @Override
    public void addHelloWorld(HelloWorld helloWorld) {
        synchronized (this) {
            this.helloWorldMessages.add(helloWorld);
        }
    }
}

package music.kmmk.backend.data;

import music.kmmk.backend.model.HelloWorld;

import java.util.List;

public interface HelloWorldRepository {

    List<HelloWorld> getAll();

    void addHelloWorld(HelloWorld helloWorld);

}

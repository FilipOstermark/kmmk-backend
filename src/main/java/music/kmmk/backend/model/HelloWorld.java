package music.kmmk.backend.model;


public class HelloWorld {

    private final String message;
    private final String extraData;

    public HelloWorld() {
        this(
                "Hello world!",
                "And here is some extra data just to show that the POJO -> JSON mapping is working properly."
        );
    }

    public HelloWorld(String message, String extraData) {
        this.message = message;
        this.extraData = extraData;
    }

    public String getMessage() {
        return this.message;
    }

    public String getExtraData() {
        return this.extraData;
    }
}

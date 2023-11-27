package music.kmmk.backend.data.http;

import java.net.http.HttpClient;

public class DefaultHttpClient {

    private static final HttpClient instance = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NEVER)
            .build();

    public static HttpClient getInstance() {
        return instance;
    }
}

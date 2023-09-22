package music.kmmk.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import music.kmmk.backend.dto.ReleaseGroupsDto;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.function.Function;

@Component
public class AlbumInfoServiceMusizBrainzImpl implements AlbumInfoService {

    private static final String URL_QUERY_RELEASE_GROUP = "https://musicbrainz.org/ws/2/release-group/";
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    /**
     * TODO Clean up!
     * @param query
     * @return
     * @throws URISyntaxException
     */
    @Override
    public ReleaseGroupsDto search(String query) throws URISyntaxException {
        System.out.println("Query: " + query);
        final String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        final HttpRequest httpRequestBuilder = HttpRequest.newBuilder()
                .uri(new URI(URL_QUERY_RELEASE_GROUP + "?query=" + encodedQuery))
                .header("Accept", "application/json")
                .header("UserAgent", "Application kmmk-backend/0.0.1 ( filip.ostermark@gmail.com )")
                .timeout(REQUEST_TIMEOUT)
                .GET()
                .build();

        final HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NEVER)
                .build();

        // Note: Could just return object as-is, but will re-serialize for learning purposes
        final ObjectMapper mapper = new ObjectMapper();
        final Function<String, ReleaseGroupsDto> mapToReleaseGroupResponse = jsonStr -> {
            try {
                return mapper.readValue(jsonStr, ReleaseGroupsDto.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        };

        final ReleaseGroupsDto response = client.sendAsync(httpRequestBuilder, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(mapToReleaseGroupResponse)
                .join();

        System.out.println(response.toString());

        return response;
    }
}

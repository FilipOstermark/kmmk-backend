package music.kmmk.backend.service;

import music.kmmk.backend.dto.ReleaseGroupsDto;
import music.kmmk.backend.mapper.AlbumInfoMapper;
import music.kmmk.backend.model.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
public class AlbumInfoServiceMusizBrainzImpl implements AlbumInfoService {

    private final AlbumInfoMapper albumInfoMapper;

    @Autowired
    public AlbumInfoServiceMusizBrainzImpl(
            AlbumInfoMapper albumInfoMapper
    ) {
        this.albumInfoMapper = albumInfoMapper;
    }

    private static final String URL_QUERY_RELEASE_GROUP = "https://musicbrainz.org/ws/2/release-group/";
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    private HttpRequest createSearchRequest(String searchQuery) throws URISyntaxException {
        final String encodedQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8);
        return HttpRequest.newBuilder()
                .uri(new URI(URL_QUERY_RELEASE_GROUP + "?query=" + encodedQuery))
                .header("Accept", "application/json")
                .header("UserAgent", "Application kmmk-backend/0.0.1 ( filip.ostermark@gmail.com )")
                .timeout(REQUEST_TIMEOUT)
                .GET()
                .build();
    }

    @Override
    public ReleaseGroupsDto search(String query) throws URISyntaxException {
        final HttpRequest httpRequestBuilder = createSearchRequest(query);
        return DefaultHttpClient
                .getInstance()
                .sendAsync(httpRequestBuilder, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                // Note: Could just return object as-is, but will re-serialize for learning purposes
                .thenApply(this.albumInfoMapper::jsonToReleaseGroupDto)
                .join();
    }
}

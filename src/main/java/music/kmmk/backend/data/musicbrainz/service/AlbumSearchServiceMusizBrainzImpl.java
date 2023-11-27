package music.kmmk.backend.data.musicbrainz.service;

import music.kmmk.backend.core.album.service.AlbumSearchService;
import music.kmmk.backend.data.musicbrainz.dto.ReleaseGroupsDto;
import music.kmmk.backend.data.musicbrainz.mapper.ReleaseGroupMapper;
import music.kmmk.backend.data.http.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Service
public class AlbumSearchServiceMusizBrainzImpl implements AlbumSearchService {

    private final ReleaseGroupMapper releaseGroupMapper;

    @Autowired
    public AlbumSearchServiceMusizBrainzImpl(
            ReleaseGroupMapper releaseGroupMapper
    ) {
        this.releaseGroupMapper = releaseGroupMapper;
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
                .thenApply(this.releaseGroupMapper::jsonToReleaseGroupsDto)
                .join();
    }
}

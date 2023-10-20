package music.kmmk.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String clientOAuth2TokenUrl;

    public String getClientOAuth2TokenUrl() {
        return clientOAuth2TokenUrl;
    }

    public String getClientOAuth2TokenUrl(String authToken) {
        return clientOAuth2TokenUrl + "?token=" + authToken;
    }

    public void setClientOAuth2TokenUrl(String clientOAuth2TokenUrl) {
        this.clientOAuth2TokenUrl = clientOAuth2TokenUrl;
    }
}

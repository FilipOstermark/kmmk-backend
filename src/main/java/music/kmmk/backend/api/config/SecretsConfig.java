package music.kmmk.backend.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "secrets")
public class SecretsConfig {

    private String authTokenSecret;
    private long authTokenExpirationMs;
    private String[] votingUsers;

    public String getAuthTokenSecret() {
        return this.authTokenSecret;
    }

    @SuppressWarnings("unused")
    public void setAuthTokenSecret(String authTokenSecret) {
        this.authTokenSecret = authTokenSecret;
    }

    public long getAuthTokenExpirationMs() {
        return authTokenExpirationMs;
    }

    @SuppressWarnings("unused")
    public void setAuthTokenExpirationMs(long authTokenExpirationMs) {
        this.authTokenExpirationMs = authTokenExpirationMs;
    }

    @SuppressWarnings("unused")
    public String[] getVotingUsers() {
        return votingUsers;
    }

    @SuppressWarnings("unused")
    public void setVotingUsers(String[] votingUsers) {
        this.votingUsers = votingUsers;
    }

}

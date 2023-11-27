package music.kmmk.backend.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import music.kmmk.backend.api.config.SecretsConfig;
import music.kmmk.backend.api.security.oauth2.model.JwtAuthentication;
import music.kmmk.backend.core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class TokenProvider {

    private final SecretsConfig secretsConfig;

    @Autowired
    public TokenProvider(SecretsConfig secretsConfig) {
        this.secretsConfig = secretsConfig;
    }

    public String create(String userEmail, String userName, String userId) {
        final Date now = new Date();
        final Date expirationDate = new Date(now.getTime() + secretsConfig.getAuthTokenExpirationMs());
        final SecretKey key = loadKey();

        return Jwts.builder()
                .setSubject(userEmail)
                .setClaims(Map.of("name", userName, "userId", userId))
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .setSubject(userEmail)
                .signWith(key)
                .compact();
    }

    /**
     * Create an authentication from the given JWT.
     * @param jwt The JWT
     * @return The authentication object.
     */
    public JwtAuthentication toAuthentication(String jwt) {
        final Claims claims = Jwts.parser()
                .setSigningKey(loadKey())
                .parseClaimsJws(jwt)
                .getBody();

        final Long userId = claims.get("userId", Long.class);
        final String name = claims.get("name", String.class);
        final String email = claims.getSubject();
        final User user = new User(userId, name, email);
        return new JwtAuthentication(user);
    }

    public boolean validate(String authToken) {
        try {
            Jwts.parser().setSigningKey(loadKey()).parseClaimsJws(authToken).getBody();
            return true;
        } catch (SignatureException ex) {
            System.err.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.err.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.err.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.err.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.err.println("JWT claims string is empty.");
        }

        return false;
    }

    private SecretKey loadKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretsConfig.getAuthTokenSecret()));
    }

}

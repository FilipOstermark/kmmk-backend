package music.kmmk.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import music.kmmk.backend.config.SecretsConfig;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenProvider {

    private final SecretsConfig secretsConfig;

    @Autowired
    public TokenProvider(SecretsConfig secretsConfig) {
        this.secretsConfig = secretsConfig;
    }

    public String create(String userEmail) {
        final Date now = new Date();
        final Date expirationDate = new Date(now.getTime() + secretsConfig.getAuthTokenExpirationMs());
        final SecretKey key = loadKey();

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .setSubject(userEmail)
                .signWith(key)
                .compact();
    }

    public String getUserEmailFromToken(String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(loadKey())
                .parseClaimsJws(token)
                .getBody();

        if (StringUtils.isNullOrEmpty(claims.getSubject())) {
            throw new IllegalArgumentException("Invalid JWT subject - missing email");
        }

        return claims.getSubject().split(";")[0];
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

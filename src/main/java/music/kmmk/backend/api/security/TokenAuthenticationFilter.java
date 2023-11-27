package music.kmmk.backend.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import music.kmmk.backend.api.security.oauth2.model.JwtAuthentication;
import music.kmmk.backend.core.util.StringExtensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            if (!hasValidAuthToken(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            final String jwt = extractBearerToken(request);
            final JwtAuthentication authentication = tokenProvider.toAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            // TODO Error handling
            System.err.println("Failed to authenticate user");
        }

        filterChain.doFilter(request, response);
    }

    private String extractBearerToken(@NonNull HttpServletRequest request) {
        final String bearerToken = request.getHeader("Authorization");
        if (StringExtensions.isNullOrBlank(bearerToken) || !bearerToken.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Request does not contain Bearer token");
        }

        return bearerToken.substring("Bearer ".length());
    }

    private boolean hasValidAuthToken(@NonNull HttpServletRequest request) {
        try {
            final String jwt = extractBearerToken(request);
            return tokenProvider.validate(jwt);
        } catch (Exception ignored) {
            // TODO Logging
        }

        return false;
    }

}

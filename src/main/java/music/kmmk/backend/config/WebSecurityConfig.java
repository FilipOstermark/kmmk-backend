package music.kmmk.backend.config;

import music.kmmk.backend.oauth2.model.GoogleOAuth2User;
import music.kmmk.backend.oauth2.service.OAuth2UserServiceGoogleImpl;
import music.kmmk.backend.security.TokenAuthenticationFilter;
import music.kmmk.backend.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * References:
 *
 * <ul>
 *     <li><a href="https://spring.io/guides/gs/securing-web/">spring.io - Securing Web</a></li>
 *     <li><a href="https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter">spring.io - Spring Security Without the WebSecurityConfigureAdapter</a></li>
 *     <li><a href="https://stackoverflow.com/questions/74683225/updating-to-spring-security-6-0-replacing-removed-and-deprecated-functionality">StackOverflow - Updating to Spring Security 6.0 [...]</a></li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private OAuth2UserServiceGoogleImpl oAuth2UserService;

    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    private TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/error", "/login/**", "/oauth2/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(this.oAuth2UserService))
                        .authorizationEndpoint(auth -> auth.baseUri("/oauth2/authorization"))
                        .redirectionEndpoint(redirection -> redirection.baseUri("/oauth2/callback/*"))
                        .successHandler((request, response, authentication) -> {
                            final GoogleOAuth2User user = (GoogleOAuth2User) authentication.getPrincipal();

                            System.out.println("OAuth user attributes:");
                            user.getAttributes().forEach((key, val) ->
                                    System.out.println("Attr -> " + key + ": " + val.toString())
                            );

                            final String authToken = this.tokenProvider.create(user.getEmail(),
                                    user.getName(),
                                    user.getId().toString());
                            response.sendRedirect("http://localhost:5173/oauth2/token?token=" + authToken);
                        })
                )
                .addFilterBefore(this.tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}

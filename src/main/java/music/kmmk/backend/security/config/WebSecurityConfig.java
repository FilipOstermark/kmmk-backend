package music.kmmk.backend.security.config;

import music.kmmk.backend.oauth2.model.FacebookOAuth2User;
import music.kmmk.backend.oauth2.service.FacebookOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;
import java.util.stream.Collectors;

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
    private FacebookOAuth2UserService oAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/error", "/login/**", "/oauth2/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(this.oAuth2UserService)
                        )
                        .successHandler((request, response, authentication) -> {
                            final FacebookOAuth2User user = (FacebookOAuth2User)authentication.getPrincipal();
                            System.out.println("RESPONSE:");
                            System.out.println("headers: " + response.getHeaderNames().stream().collect(Collectors.toMap(headerName -> headerName, response::getHeader)));
                            System.out.println("status:  " + response.getStatus());
                            System.out.println("isAuth:  " + authentication.isAuthenticated());

                            this.oAuth2UserService.saveUser(user);

                            response.sendRedirect("http://localhost:5173/oauth2/token?token=");
                        })
                )
                // TODO How to handle this?
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}

package music.kmmk.backend.security.config;

import music.kmmk.backend.oauth2.model.FacebookOAuth2User;
import music.kmmk.backend.oauth2.service.FacebookOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
                        .requestMatchers("/", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(this.oAuth2UserService)
                        )
                        .successHandler((request, response, authentication) -> {
                            final FacebookOAuth2User user = (FacebookOAuth2User)authentication.getPrincipal();

                            this.oAuth2UserService.saveUser(user);

                            response.sendRedirect("/user/self");
                        })
                )
                .logout(logout -> logout.logoutSuccessUrl("/logout"))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

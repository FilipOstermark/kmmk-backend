package music.kmmk.backend.config;

import music.kmmk.backend.security.DevAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
@Profile("dev")
public class WebSecurityConfigDev {

    @Autowired
    private DevAuthenticationFilter devAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
                .addFilterBefore(this.devAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}

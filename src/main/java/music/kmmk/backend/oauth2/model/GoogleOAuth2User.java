package music.kmmk.backend.oauth2.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class GoogleOAuth2User implements OAuth2User {
    private final OAuth2User user;

    public GoogleOAuth2User(OAuth2User user) {
        this.user = user;
    }

    @Override
    public <A> A getAttribute(String name) {
        return this.user.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.user.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getAuthorities();
    }

    @Override
    public String getName() {
        return this.user.getAttribute("name");
    }

    public String getEmail() {
        return this.user.getAttribute("email");
    }
}

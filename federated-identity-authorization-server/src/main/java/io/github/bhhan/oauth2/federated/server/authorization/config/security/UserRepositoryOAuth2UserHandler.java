package io.github.bhhan.oauth2.federated.server.authorization.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {
    @Override
    public void accept(OAuth2User user) {
        System.out.println("Saving user: name=" + user.getName() + ", claims=" + user.getAttributes() + ", authorities=" + user.getAuthorities());
    }
}

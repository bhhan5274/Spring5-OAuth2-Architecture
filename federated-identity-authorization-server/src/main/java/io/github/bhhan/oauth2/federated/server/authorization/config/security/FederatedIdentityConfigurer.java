package io.github.bhhan.oauth2.federated.server.authorization.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.function.Consumer;

public class FederatedIdentityConfigurer extends AbstractHttpConfigurer<FederatedIdentityConfigurer, HttpSecurity> {
    private String loginPageUrl = "/login";
    private Consumer<OAuth2User> oauth2UserHandler;
    private Consumer<OidcUser> oidcUserHandler;
    private DefaultOAuth2UserService defaultOAuth2UserService;

    public FederatedIdentityConfigurer defaultOAuth2UserService(DefaultOAuth2UserService defaultOAuth2UserService) {
        Objects.requireNonNull(defaultOAuth2UserService, "defaultOAuth2UserService cannot be null");
        this.defaultOAuth2UserService = defaultOAuth2UserService;
        return this;
    }

    public FederatedIdentityConfigurer loginPageUrl(String loginPageUrl) {
        Assert.hasText(loginPageUrl, "loginPageUrl cannot be empty");
        this.loginPageUrl = loginPageUrl;
        return this;
    }

    public FederatedIdentityConfigurer oauth2UserHandler(Consumer<OAuth2User> oauth2UserHandler) {
        Objects.requireNonNull(oauth2UserHandler, "oauth2UserHandler cannot be null");
        this.oauth2UserHandler = oauth2UserHandler;
        return this;
    }

    public FederatedIdentityConfigurer oidcUserHandler(Consumer<OidcUser> oidcUserHandler) {
        Objects.requireNonNull(oidcUserHandler, "oidcUserHandler cannot be null");
        this.oidcUserHandler = oidcUserHandler;
        return this;
    }

    @Override
    public void init(HttpSecurity http) throws Exception {
        FederatedIdentityAuthenticationEntryPoint authenticationEntryPoint = new FederatedIdentityAuthenticationEntryPoint(this.loginPageUrl);

        FederatedIdentityAuthenticationSuccessHandler authenticationSuccessHandler = new FederatedIdentityAuthenticationSuccessHandler();
        if (this.oauth2UserHandler != null) {
            authenticationSuccessHandler.setOauth2UserHandler(this.oauth2UserHandler);
        }

        if (this.oidcUserHandler != null) {
            authenticationSuccessHandler.setOidcUserHandler(this.oidcUserHandler);
        }

        http
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(authenticationEntryPoint)
                )
                .oauth2Login(oauth2Login -> {
                    if (this.defaultOAuth2UserService != null) {
                        oauth2Login.userInfoEndpoint()
                                .userService(defaultOAuth2UserService);
                    }
                    oauth2Login.successHandler(authenticationSuccessHandler);
                });
    }
}

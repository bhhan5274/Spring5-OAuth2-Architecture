package io.github.bhhan.oauth2.federated.server.authorization.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FederatedIdentityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final AuthenticationEntryPoint delegate;

    public FederatedIdentityAuthenticationEntryPoint(String loginPageUrl) {
        this.delegate = new LoginUrlAuthenticationEntryPoint(loginPageUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        this.delegate.commence(request, response, authException);
    }
}

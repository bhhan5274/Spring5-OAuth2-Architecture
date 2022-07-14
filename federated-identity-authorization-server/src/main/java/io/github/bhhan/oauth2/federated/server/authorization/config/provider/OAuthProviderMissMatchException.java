package io.github.bhhan.oauth2.federated.server.authorization.config.provider;

public class OAuthProviderMissMatchException extends RuntimeException{
    private String message;

    public OAuthProviderMissMatchException(String message) {
        super(message);
    }
}

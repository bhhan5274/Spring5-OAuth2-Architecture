package io.github.bhhan.oauth2.federated.server.authorization.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FederatedIdentityIdTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    private static final Set<String> ID_TOKEN_CLAIMS = Set.of(IdTokenClaimNames.ISS, IdTokenClaimNames.SUB, IdTokenClaimNames.AUD, IdTokenClaimNames.EXP, IdTokenClaimNames.IAT, IdTokenClaimNames.AUTH_TIME, IdTokenClaimNames.NONCE, IdTokenClaimNames.ACR, IdTokenClaimNames.AMR, IdTokenClaimNames.AZP, IdTokenClaimNames.AT_HASH, IdTokenClaimNames.C_HASH);

    @Override
    public void customize(JwtEncodingContext context) {
        if(OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue()))  {
            Map<String, Object> thirdPartyClaims = extractClaims(context.getPrincipal());
            context.getClaims().claims(existingClaims -> {
                existingClaims.keySet().forEach(thirdPartyClaims::remove);
                ID_TOKEN_CLAIMS.forEach(thirdPartyClaims::remove);
                existingClaims.putAll(thirdPartyClaims);
            });
        }
    }

    private Map<String, Object> extractClaims(Authentication principal) {
        Map<String, Object> claims;

        if(principal.getPrincipal() instanceof OidcUser) {
            OidcUser oidcUser = (OidcUser) principal.getPrincipal();
            OidcIdToken idToken = oidcUser.getIdToken();
            claims = idToken.getClaims();
        }else if(principal.getPrincipal() instanceof OAuth2User) {
            OAuth2User oAuth2User = (OAuth2User) principal.getPrincipal();
            claims = oAuth2User.getAttributes();
        }else {
            claims = Collections.emptyMap();
        }

        return new HashMap<>(claims);
    }
}

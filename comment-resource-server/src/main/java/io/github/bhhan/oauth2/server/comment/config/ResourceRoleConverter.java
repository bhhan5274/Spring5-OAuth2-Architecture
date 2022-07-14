package io.github.bhhan.oauth2.server.comment.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class ResourceRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    @SuppressWarnings("unchecked")
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        List<String> roles = (List<String>) jwt.getClaims()
                .get("roles");
        List<String> scopes = (List<String>) jwt.getClaims()
                .get("scope");

        if (Objects.isNull(roles) && Objects.isNull(scopes)) {
            return new ArrayList<>();
        }

        List<String> authorities = new ArrayList<>(Objects.nonNull(roles) ? roles : Collections.emptyList());

        for (String scope : scopes) {
            authorities.add("SCOPE_" + scope);
        }

        return authorities.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}

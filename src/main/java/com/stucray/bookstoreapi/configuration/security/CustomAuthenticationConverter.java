package com.stucray.bookstoreapi.configuration.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Custom converter to extract granted authorities from roles property in JWT.
 *
 * This works with Keycloak using custom mapper for roles.
 */
@Component
public class CustomAuthenticationConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {
    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {

        Collection<String> roleClaims = (Collection<String>)jwt.getClaims().get("roles");
        var authorities = roleClaims.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return Mono.just(new JwtAuthenticationToken(jwt, authorities));
    }
}

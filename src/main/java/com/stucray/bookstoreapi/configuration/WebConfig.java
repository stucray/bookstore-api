package com.stucray.bookstoreapi.configuration;

import com.stucray.bookstoreapi.configuration.security.CustomAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.List;

/**
 * Web security configuration.
 */
@Configuration
@EnableWebFlux
@RequiredArgsConstructor
public class WebConfig {

    private final CustomAuthenticationConverter converter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/books/**").hasAnyRole("USER", "ADMIN")
                .anyExchange().authenticated()
                .and().cors().configurationSource(configurationSource())
                .and().oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(converter);

        return http.build();
    }


    private CorsConfigurationSource configurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));

        source.registerCorsConfiguration("/**", config);
        return source;
    }

}

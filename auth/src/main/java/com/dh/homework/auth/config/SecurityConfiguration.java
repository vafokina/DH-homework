package com.dh.homework.auth.config;

import com.dh.homework.auth.security.KeycloakLogoutHandler;
import javax.sql.DataSource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfiguration {

    KeycloakLogoutHandler keycloakLogoutHandler;

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry -> registry
                .requestMatchers("/auth/token*").anonymous()
                .anyRequest().authenticated()
        )
                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> {})
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.addLogoutHandler(keycloakLogoutHandler))
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
                        httpSecurityOAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults()));
        return http.build();
    }
}

package com.dh.homework.auth.config;

import com.dh.homework.auth.config.properties.KeycloakProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.keycloak.authorization.client.AuthzClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KeycloakConfiguration {

    KeycloakProperties keycloakProperties;

    @Bean
    public AuthzClient keycloakAuthzClient() {
        val config = new org.keycloak.authorization.client.Configuration(
                keycloakProperties.getAuthServerUrl(), keycloakProperties.getRealm(),
                keycloakProperties.getClientId(), keycloakProperties.getCredentials(), null);

        return AuthzClient.create(config);
    }
}

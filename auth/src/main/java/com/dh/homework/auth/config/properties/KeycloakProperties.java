package com.dh.homework.auth.config.properties;

import java.util.Map;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KeycloakProperties {

    String authServerUrl;
    String realm;
    String clientId;
    Map<String, Object> credentials;

}

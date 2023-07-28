package com.dh.homework.auth.web.rest;

import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    RestTemplate restTemplate;

    @Value("spring.security.oauth2.client.registration.keycloak.client-id")
    String clientId = "";

    @Value("spring.security.oauth2.client.provider.keycloak.issuer-uri")
    String issuerUri = "";

    @GetMapping
    public ResponseEntity<String> authentication(Principal principal) {
        if (principal instanceof OidcIdToken oidcIdToken) {
            return ResponseEntity.ok(oidcIdToken.getTokenValue());
        } else {
            throw new OAuth2AuthenticationException("unauthorized");
        }
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> token(@RequestParam String username, @RequestParam String password) {
        val headers = new HttpHeaders();
        val map = new HashMap<String, String>();
        map.put("client_id", clientId);
        map.put("username", username);
        map.put("password", password);
        map.put("grant_type", "password");
        val entity = new RequestEntity<Map<String, String>>(map, headers, HttpMethod.POST, URI.create(issuerUri + "/protocol/openid-connect/token"));
        ParameterizedTypeReference<Map<String, String>> responseType = new ParameterizedTypeReference<>() {};

        return restTemplate.exchange(entity, responseType);
    }
}

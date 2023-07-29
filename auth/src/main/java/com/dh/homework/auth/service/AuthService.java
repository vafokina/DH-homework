package com.dh.homework.auth.service;

import com.dh.homework.auth.dto.LoginResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.util.Http;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    AuthzClient authzClient;

    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    String issuerUri;

    public LoginResponseDto getToken(String username, String password) {
        log.info("START getToken for user {}", username);
        try {
            val response = authzClient.authorization(username, password)
                    .authorize();
            val result = new LoginResponseDto()
                    .setTokenType(response.getTokenType())
                    .setToken(response.getToken())
                    .setRefreshToken(response.getRefreshToken());
            log.info("FINISH getToken for user {} successfully", username);
            return result;
        } catch (Exception ex) {
            log.error("Some error occurred during getToken", ex);
            throw ex;
        }
    }

    public LoginResponseDto getRefreshToken(String refreshToken) {
        log.info("START getRefreshToken");
        try {
            String url = issuerUri + "/protocol/openid-connect/token";
            String clientId = authzClient.getConfiguration().getResource();
            String secret = (String) authzClient.getConfiguration().getCredentials().get("secret");
            val http = new Http(authzClient.getConfiguration(), (params, headers) -> { });

            val response = http.<AccessTokenResponse>post(url)
                    .authentication()
                    .client()
                    .form()
                    .param("grant_type", "refresh_token")
                    .param("refresh_token", refreshToken)
                    .param("client_id", clientId)
                    .param("client_secret", secret)
                    .response()
                    .json(AccessTokenResponse.class)
                    .execute();

            val result = new LoginResponseDto()
                    .setTokenType(response.getTokenType())
                    .setToken(response.getToken())
                    .setRefreshToken(response.getRefreshToken());
            log.info("FINISH getRefreshToken");
            return result;
        } catch (Exception ex) {
            log.error("Some error occurred during getRefreshToken", ex);
            throw ex;
        }
    }
}

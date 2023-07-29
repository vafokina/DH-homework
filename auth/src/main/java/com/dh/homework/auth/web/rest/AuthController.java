package com.dh.homework.auth.web.rest;

import com.dh.homework.auth.dto.LoginResponseDto;
import com.dh.homework.auth.service.AuthService;
import java.security.Principal;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

    @GetMapping
    public ResponseEntity<String> authentication(Principal principal) {
        if (principal instanceof OidcIdToken oidcIdToken) {
            return ResponseEntity.ok(oidcIdToken.getTokenValue());
        } else {
            throw new OAuth2AuthenticationException("unauthorized");
        }
    }

    @PostMapping("/token")
    public LoginResponseDto tokenV2(@RequestParam String username, @RequestParam String password) {
        return authService.getToken(username, password);
    }

    @PostMapping("/tokenRefresh")
    public LoginResponseDto tokenRefresh(@RequestParam String refreshToken) {
        return authService.getRefreshToken(refreshToken);
    }
}

package com.dh.homework.auth.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginResponseDto {

    private String token;

    private String refreshToken;

    private String tokenType;
}

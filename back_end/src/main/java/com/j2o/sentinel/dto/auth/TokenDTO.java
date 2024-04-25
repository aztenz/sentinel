package com.j2o.sentinel.dto.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenDTO {
    private static final String TOKEN_IS_MANDATORY = "token is mandatory";
    @Valid

    @NotNull(message = TOKEN_IS_MANDATORY)
    @NotBlank(message = TOKEN_IS_MANDATORY)
    private String token;
}

package com.j2o.sentinel.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TokenDTO {
    private static final String TOKEN_IS_MANDATORY = "token is mandatory";
    @Valid

    @NotNull(message = TOKEN_IS_MANDATORY)
    @NotEmpty(message = TOKEN_IS_MANDATORY)
    private String token;
}

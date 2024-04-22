package com.j2o.sentinel.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {
    private static final String USERNAME_IS_MANDATORY = "username is mandatory";
    private static final String PASSWORD_IS_MANDATORY = "password is mandatory";
    @Valid


    @NotNull(message = USERNAME_IS_MANDATORY)
    @NotEmpty(message = USERNAME_IS_MANDATORY)
    private String username;

    @NotNull(message = PASSWORD_IS_MANDATORY)
    @NotEmpty(message = PASSWORD_IS_MANDATORY)
    private String password;
}

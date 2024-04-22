package com.j2o.sentinel.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RegisterDTO {
    private static final String USERNAME_IS_MANDATORY = "username is mandatory";
    private static final String PASSWORD_IS_MANDATORY = "password is mandatory";
    private static final String EMAIL_IS_MANDATORY = "email is mandatory";
    private static final String PLEASE_ENTER_A_CORRECT_EMAIL_FORMAT = "please enter a correct email format";
    private static final String EMAIL_REGEXP = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$\n";
    @Valid

    @NotNull(message = USERNAME_IS_MANDATORY)
    @NotEmpty(message = USERNAME_IS_MANDATORY)
    private String username;

    @NotNull(message = PASSWORD_IS_MANDATORY)
    @NotEmpty(message = PASSWORD_IS_MANDATORY)
    private String password;

    @NotNull(message = EMAIL_IS_MANDATORY)
    @NotEmpty(message = EMAIL_IS_MANDATORY)
    @Email(regexp = EMAIL_REGEXP, message = PLEASE_ENTER_A_CORRECT_EMAIL_FORMAT)
    private String email;
}

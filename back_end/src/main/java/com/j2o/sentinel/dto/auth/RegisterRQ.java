package com.j2o.sentinel.dto.auth;

import com.j2o.sentinel.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRQ {
    private String username;

    private String password;

    private Role role;

    private String email;
}

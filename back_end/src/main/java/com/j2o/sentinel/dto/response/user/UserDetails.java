package com.j2o.sentinel.dto.response.user;

import com.j2o.sentinel.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDetails {
    private String username;
    private String email;
    private Role role;
}

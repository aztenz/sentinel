package com.j2o.sentinel.controller;

import com.j2o.sentinel.dto.auth.LoginRQ;
import com.j2o.sentinel.dto.auth.RegisterRQ;
import com.j2o.sentinel.dto.auth.TokenDTO;
import com.j2o.sentinel.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(
            @Valid @RequestBody RegisterRQ request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(
            @Valid @RequestBody LoginRQ loginUserDTO
    ) {
        return ResponseEntity.ok(authenticationService.login(loginUserDTO));
    }

}

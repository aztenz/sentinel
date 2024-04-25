package com.j2o.sentinel.service;

import com.j2o.sentinel.dto.auth.LoginRQ;
import com.j2o.sentinel.dto.auth.RegisterRQ;
import com.j2o.sentinel.dto.auth.TokenDTO;
import com.j2o.sentinel.model.Token;
import com.j2o.sentinel.model.User;
import com.j2o.sentinel.enums.Role;
import com.j2o.sentinel.enums.TokenType;
import com.j2o.sentinel.exception.DuplicateItemException;
import com.j2o.sentinel.exception.ItemNotFoundException;
import com.j2o.sentinel.repository.TokenRepository;
import com.j2o.sentinel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final String USER_NOT_FOUND = "Couldn't Find User: ";
    private static final String USERNAME_ALREADY_EXISTS = "Username already exists: ";
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenDTO register(RegisterRQ registerRQ) {
        validateDuplicateUser(registerRQ.getUsername());
        User user = createUser(registerRQ);
        userRepository.save(user);
        return generateAuthenticationToken(registerRQ.getUsername());
    }

    public TokenDTO login(LoginRQ loginRQ) {
        authenticateUser(loginRQ.getUsername(), loginRQ.getPassword());

        User user = userRepository.findByUsername(loginRQ.getUsername()).orElseThrow(
                () -> new ItemNotFoundException(USER_NOT_FOUND + loginRQ.getUsername())
        );

        revokeAllTokens(user);

        return generateAuthenticationToken(loginRQ.getUsername());
    }

    private void revokeAllTokens(User user) {
        List<Token> validTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        validTokens.forEach(token -> {
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validTokens);
    }

    private void authenticateUser(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
    }

    private void validateDuplicateUser(String username) {
        userRepository.findByUsername(username)
                .ifPresent(u -> {
                    throw new DuplicateItemException(USERNAME_ALREADY_EXISTS + username);
                });
    }

    private User createUser(RegisterRQ registerRQ) {
        return User.builder()
                .username(registerRQ.getUsername())
                .password(passwordEncoder.encode(registerRQ.getPassword()))
                .role(Role.CUSTOMER)
                .build();
    }

    private TokenDTO generateAuthenticationToken(String username) {
        String jwtToken = jwtService.generateToken(username);
        Token token = Token.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .build();

        token.setUser(userRepository.findByUsername(username)
                .orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND + username)));

        tokenRepository.save(token);

        return new TokenDTO(jwtToken);
    }
}

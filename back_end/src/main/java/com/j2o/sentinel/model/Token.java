package com.j2o.sentinel.model;

import com.j2o.sentinel.enums.TokenType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "token", unique = true)
    private String token;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "token_type")
    private TokenType tokenType;

    @Column(name = "revoked")
    private Boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

package com.j2o.sentinel.repository;

import com.j2o.sentinel.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("SELECT t " +
            "FROM Token t " +
            "INNER JOIN User u " +
            "ON t.user.id = u.id " +
            "WHERE u.id = :userId " +
            "AND t.revoked = FALSE")
    List<Token> findAllValidTokensByUser(int userId);

    Optional<Token> findByToken(String token);
}
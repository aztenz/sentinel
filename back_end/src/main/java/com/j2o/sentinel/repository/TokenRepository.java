package com.j2o.sentinel.repository;

import com.j2o.sentinel.model.Token;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends GenericRepository<Token, Integer> {

    @Query("SELECT t " +
            "FROM Token t " +
            "INNER JOIN User u " +
            "ON t.user.id = u.id " +
            "WHERE u.id = :userId " +
            "AND (t.expired = FALSE OR t.revoked = FALSE)")
    List<Token> findAllValidTokensByUser(int userId);

    Optional<Token> findByToken(String token);
}
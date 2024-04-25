package com.j2o.sentinel.repository;

import com.j2o.sentinel.model.User;
import java.util.Optional;

public interface UserRepository extends GenericRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}

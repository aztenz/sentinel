package com.j2o.sentinel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<E, ID> extends JpaRepository<E, ID> {
}

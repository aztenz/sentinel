package com.j2o.sentinel.repository;

import com.j2o.sentinel.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

package com.techweek.n8n_demo.repository;

import com.techweek.n8n_demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(Order.OrderStatus status);
    List<Order> findByOrderDateBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
}

package com.karlemstrand.webshopspringbootrestapi.repository;

import com.karlemstrand.webshopspringbootrestapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByUserId(UUID userId);

    List<Order> findAllByCreationTimestampBetween(Timestamp start, Timestamp end);

}

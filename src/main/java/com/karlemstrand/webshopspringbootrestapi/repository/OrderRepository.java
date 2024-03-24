package com.karlemstrand.webshopspringbootrestapi.repository;

import com.karlemstrand.webshopspringbootrestapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

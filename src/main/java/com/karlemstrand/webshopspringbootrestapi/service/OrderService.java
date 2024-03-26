package com.karlemstrand.webshopspringbootrestapi.service;

import com.karlemstrand.webshopspringbootrestapi.dtos.OrderDto;
import com.karlemstrand.webshopspringbootrestapi.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getAllOrdersByUserId(UUID userId);

    OrderDto createOrder(OrderDto orderDto);
}

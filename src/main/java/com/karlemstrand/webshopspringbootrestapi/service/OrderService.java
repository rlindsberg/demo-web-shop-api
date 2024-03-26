package com.karlemstrand.webshopspringbootrestapi.service;

import com.karlemstrand.webshopspringbootrestapi.dtos.OrderDto;
import com.karlemstrand.webshopspringbootrestapi.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    OrderDto createOrder(OrderDto orderDto);
}

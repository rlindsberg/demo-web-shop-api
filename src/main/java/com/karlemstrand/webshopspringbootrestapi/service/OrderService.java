package com.karlemstrand.webshopspringbootrestapi.service;

import com.karlemstrand.webshopspringbootrestapi.dtos.OrderDto;
import com.karlemstrand.webshopspringbootrestapi.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getAllOrdersByUserId(UUID userId);
    // a date has format "2000-01-02 13:04:56.123456"
    double getTotalPriceByUserIdAndDateRange(UUID userId, String startDate, String endDate);

    OrderDto createOrder(OrderDto orderDto);
}

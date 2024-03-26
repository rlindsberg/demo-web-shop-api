package com.karlemstrand.webshopspringbootrestapi.controller;

import com.karlemstrand.webshopspringbootrestapi.dtos.OrderDto;
import com.karlemstrand.webshopspringbootrestapi.entity.Order;
import com.karlemstrand.webshopspringbootrestapi.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/ordersByUserId")
    public ResponseEntity<List<Order>> getAllOrdersByUserId(@RequestParam(value = "userId", required = true) UUID userId){
        return new ResponseEntity<>(orderService.getAllOrdersByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/totalPriceByUserIdAndDateRange")
    public double getTotalPriceByUserIdAndDateRange(
            @RequestParam(value = "userId", required = true) UUID userId,
            @RequestParam(value = "start", required = true) String start,
            @RequestParam(value = "end", required = true) String end
    ){
        return orderService.getTotalPriceByUserIdAndDateRange(userId, start, end);
    }


    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.OK);
    }
}

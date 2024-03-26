package com.karlemstrand.webshopspringbootrestapi.service.impl;

import com.karlemstrand.webshopspringbootrestapi.entity.Order;
import com.karlemstrand.webshopspringbootrestapi.entity.Product;
import com.karlemstrand.webshopspringbootrestapi.exception.ResourceNotFoundException;
import com.karlemstrand.webshopspringbootrestapi.repository.OrderRepository;
import com.karlemstrand.webshopspringbootrestapi.repository.ProductRepository;
import com.karlemstrand.webshopspringbootrestapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public ProductServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Set<Product> getProductsByOrderId(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(
                "Order", "UUID", orderId.toString()));

        return order.getProducts();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


}

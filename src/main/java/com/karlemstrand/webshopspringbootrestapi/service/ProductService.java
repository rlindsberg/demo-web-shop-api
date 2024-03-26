package com.karlemstrand.webshopspringbootrestapi.service;

import com.karlemstrand.webshopspringbootrestapi.entity.Product;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProductService {
    Set<Product> getProductsByOrderId(UUID orderId);
    List<Product> getAllProducts();
}

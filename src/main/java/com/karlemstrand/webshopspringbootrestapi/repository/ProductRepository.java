package com.karlemstrand.webshopspringbootrestapi.repository;

import com.karlemstrand.webshopspringbootrestapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

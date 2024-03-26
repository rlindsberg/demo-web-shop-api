package com.karlemstrand.webshopspringbootrestapi.repository;

import com.karlemstrand.webshopspringbootrestapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}

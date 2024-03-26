package com.karlemstrand.webshopspringbootrestapi.dtos;

import com.karlemstrand.webshopspringbootrestapi.entity.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private UUID userId;
    private BigDecimal totalAmount;
    private Set<Product> products;
}

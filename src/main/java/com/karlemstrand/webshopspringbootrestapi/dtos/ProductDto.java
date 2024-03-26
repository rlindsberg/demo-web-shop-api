package com.karlemstrand.webshopspringbootrestapi.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductDto {
    private UUID id;
    private String name;
    private BigDecimal price;
    private int quantity;
}

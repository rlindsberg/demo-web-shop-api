package com.karlemstrand.webshopspringbootrestapi.controller;

import com.karlemstrand.webshopspringbootrestapi.entity.Product;
import com.karlemstrand.webshopspringbootrestapi.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")

    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}

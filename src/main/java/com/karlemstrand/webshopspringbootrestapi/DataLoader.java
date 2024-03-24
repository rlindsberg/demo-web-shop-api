package com.karlemstrand.webshopspringbootrestapi;

import com.karlemstrand.webshopspringbootrestapi.entity.Order;
import com.karlemstrand.webshopspringbootrestapi.entity.Product;
import com.karlemstrand.webshopspringbootrestapi.entity.User;
import com.karlemstrand.webshopspringbootrestapi.repository.OrderRepository;
import com.karlemstrand.webshopspringbootrestapi.repository.ProductRepository;
import com.karlemstrand.webshopspringbootrestapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public DataLoader(UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var p1 = new Product();
        p1.setName("Milk");
        p1.setPrice(BigDecimal.valueOf(13.99));
        p1.setStock(10);

        var p2 = new Product();
        p2.setName("Chocolate");
        p2.setPrice(BigDecimal.valueOf(23.99));
        p2.setStock(10);

        productRepository.save(p1);
        productRepository.save(p2);


        var u1 = new User();
        u1.setName("A");
        u1.setAddress("addr a");
        u1.setEmail("a@company.com");
        u1.setPhoneNumber("0700000000");

        userRepository.save(u1);

        var o1 = new Order();
        o1.setUser(u1);

        orderRepository.save(o1);

    }
}

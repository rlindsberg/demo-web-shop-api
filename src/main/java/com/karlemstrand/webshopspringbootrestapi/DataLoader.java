package com.karlemstrand.webshopspringbootrestapi;

import com.karlemstrand.webshopspringbootrestapi.dtos.OrderDto;
import com.karlemstrand.webshopspringbootrestapi.entity.Order;
import com.karlemstrand.webshopspringbootrestapi.entity.Product;
import com.karlemstrand.webshopspringbootrestapi.entity.User;
import com.karlemstrand.webshopspringbootrestapi.repository.OrderRepository;
import com.karlemstrand.webshopspringbootrestapi.repository.ProductRepository;
import com.karlemstrand.webshopspringbootrestapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    public DataLoader(UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        var p1 = new Product();
        p1.setName("Milk");
        p1.setPrice(13.99);
        p1.setQuantity(10);

        var p2 = new Product();
        p2.setName("Chocolate");
        p2.setPrice(23.99);
        p2.setQuantity(10);

        productRepository.save(p1);
        productRepository.save(p2);


        var u1 = new User();
        u1.setName("A");
        u1.setAddress("addr a");
        u1.setEmail("a@company.com");
        u1.setPhoneNumber("0700000000");

        var u2 = new User();
        u2.setName("B");
        u2.setAddress("addr b");
        u2.setEmail("b@company.com");
        u2.setPhoneNumber("0700000000");
        userRepository.save(u1);
        userRepository.save(u2);

        var o1 = new Order();
        o1.setTotalAmount(BigDecimal.valueOf(p1.getPrice()));
        o1.setCreationTimestamp(new Timestamp(System.currentTimeMillis()));
        o1.setUser(u1);


        orderRepository.save(o1);

        Set<Product> products = new HashSet<>(Arrays.asList(p1, p2));
        o1.setProducts(products);

        orderRepository.save(o1);

        var t = modelMapper.map(o1, OrderDto.class);
        System.out.println(t);

    }
}

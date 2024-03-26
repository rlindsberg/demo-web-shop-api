package com.karlemstrand.webshopspringbootrestapi.service.impl;

import com.karlemstrand.webshopspringbootrestapi.dtos.OrderDto;
import com.karlemstrand.webshopspringbootrestapi.entity.Order;
import com.karlemstrand.webshopspringbootrestapi.entity.Product;
import com.karlemstrand.webshopspringbootrestapi.repository.OrderRepository;
import com.karlemstrand.webshopspringbootrestapi.repository.ProductRepository;
import com.karlemstrand.webshopspringbootrestapi.service.OrderService;
import com.karlemstrand.webshopspringbootrestapi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.karlemstrand.webshopspringbootrestapi.utils.AppConstants.*;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, ProductService productService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Order> getAllOrders() {

        List<Order> res = orderRepository.findAll();
//        System.out.println(res.get(0));
//        List<Product> products = productRepository.findAllByOrderId(res.get(0).getId());
        return res;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);

        // Compute total amount in kr
        double total_price = 0;
        double subtotal;

        Set<Product> productSet = order.getProducts();
        List<Product> products = new ArrayList<>(productSet);
        int n_products = products.size();

        for (int i = 0; i < n_products; i++) {
            double discount;

            // calculate subtotal
            double price = products.get(i).getPrice();
            int quantity = products.get(i).getQuantity();

            // apply discount
            if (quantity > DISCOUNT_15_THRESHOLD){
                discount = DISCOUNT_15_PERCENT;
            } else if (quantity >= DISCOUNT_10_THRESHOLD) {
                discount = DISCOUNT_10_PERCENT;
            } else discount = DISCOUNT_0_PERCENT;

            subtotal = price * quantity * discount;

            // update total price
            total_price = total_price + subtotal;
        }


        // my company is broke, hence always round up
        BigDecimal total_price_rounded = new BigDecimal(total_price).setScale(2, RoundingMode.UP);

        order.setTotalAmount(total_price_rounded);

        Order res = orderRepository.save(order);

        return modelMapper.map(res, OrderDto.class);
    }
}

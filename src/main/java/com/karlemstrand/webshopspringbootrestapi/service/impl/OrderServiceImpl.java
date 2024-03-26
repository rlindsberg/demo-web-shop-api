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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public List<Order> getAllOrdersByUserId(UUID userId) {

        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public double getTotalPriceByUserIdAndDateRange(UUID userId, String startDateString, String endDateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");
            Date startDate = dateFormat.parse(startDateString);
            Timestamp start = new java.sql.Timestamp(startDate.getTime());
            Date endDate = dateFormat.parse(endDateString);
            Timestamp end = new java.sql.Timestamp(endDate.getTime());

            List<Order> res = orderRepository.findAllByCreationTimestampBetween(start, end);

            // calculate price
            double total_price = 0;
            for (int i = 0; i < res.size(); i++) {
                Order order = res.get(i);
                if (userId.equals(order.getUser().getId())){
                    total_price += order.getTotalAmount().doubleValue();
                }
            }
            return total_price;

        } catch(Exception e) { //this generic but you can control another types of exception
            throw new RuntimeException("your date format is wrong!");
        }

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

        order.setCreationTimestamp(new Timestamp(System.currentTimeMillis()));

        Order res = orderRepository.save(order);

        return modelMapper.map(res, OrderDto.class);
    }
}

package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private OrderService orderService;

    @GetMapping
    public List<Order> getUserOrders(Authentication auth) {
        String email = auth.getName();
        return orderService.getOrdersByUser(email);
    }

    @PostMapping("/place")
    public Order placeOrder(Authentication auth) {
        String email = auth.getName();
        return orderService.placeOrder(email);
    }
}

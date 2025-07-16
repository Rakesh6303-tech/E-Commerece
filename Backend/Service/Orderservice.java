package com.example.ecommerce.service;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepo;
    @Autowired private CartItemRepository cartRepo;
    @Autowired private UserRepository userRepo;

    public Order placeOrder(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        List<CartItem> cartItems = cartRepo.findByUser(user);

        double total = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setTotalAmount(total);
        orderRepo.save(order);

        cartRepo.deleteByUser(user); // Clear cart after placing order

        return order;
    }

    public List<Order> getOrdersByUser(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        return orderRepo.findByUser(user);
    }
}

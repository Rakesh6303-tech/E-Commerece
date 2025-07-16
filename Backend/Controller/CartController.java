package com.example.ecommerce.controller;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired private CartService cartService;
    @Autowired private UserService userService;

    @GetMapping
    public List<CartItem> getUserCart(Authentication auth) {
        String email = auth.getName();
        return cartService.getCartItems(email);
    }

    @PostMapping("/add")
    public CartItem addToCart(Authentication auth, @RequestParam Long productId, @RequestParam int quantity) {
        String email = auth.getName();
        return cartService.addToCart(email, productId, quantity);
    }

    @DeleteMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
    }

    @PutMapping("/update/{id}")
    public CartItem updateCartItem(@PathVariable Long id, @RequestParam int quantity) {
        return cartService.updateCartItem(id, quantity);
    }

    @DeleteMapping("/clear")
    public void clearCart(Authentication auth) {
        String email = auth.getName();
        cartService.clearCart(email);
    }
}

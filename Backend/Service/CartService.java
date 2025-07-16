package com.example.ecommerce.service;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired private CartItemRepository cartRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private UserRepository userRepo;

    public List<CartItem> getCartItems(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        return cartRepo.findByUser(user);
    }

    public CartItem addToCart(String email, Long productId, int quantity) {
        User user = userRepo.findByEmail(email).orElseThrow();
        Product product = productRepo.findById(productId).orElseThrow();

        CartItem item = new CartItem();
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(quantity);
        return cartRepo.save(item);
    }

    public void removeFromCart(Long id) {
        cartRepo.deleteById(id);
    }

    public CartItem updateCartItem(Long id, int quantity) {
        CartItem item = cartRepo.findById(id).orElseThrow();
        item.setQuantity(quantity);
        return cartRepo.save(item);
    }

    public void clearCart(String email) {
        User user = userRepo.findByEmail(email).orElseThrow();
        cartRepo.deleteByUser(user);
    }
}

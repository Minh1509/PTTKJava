package org.example.projectfinal.services;

import org.example.projectfinal.entity.Cart;

public interface CartService {
    Cart getCartByUserId(Long userId);
    void addProductToCart(Long userId, Long productId, int quantity);
    void removeProductFromCart(Long userId, Long productId);
    void clearCart(Long userId);
    double calculateTotalPrice(Cart cart);
}

package org.example.projectfinal.services;

import org.aspectj.weaver.ast.Or;
import org.example.projectfinal.entity.Cart;
import org.example.projectfinal.entity.Order;
import org.example.projectfinal.entity.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, Cart cart);
    List<Order> getOrdersByUser(Long userId);

}

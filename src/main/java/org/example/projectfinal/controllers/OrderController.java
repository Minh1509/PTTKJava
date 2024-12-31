package org.example.projectfinal.controllers;

import org.example.projectfinal.entity.*;
import org.example.projectfinal.services.OrderService;
import org.example.projectfinal.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam("userId") Long userId, Model model) {
        Cart cart = cartService.getCartByUserId(userId);

        if (cart == null) {
            model.addAttribute("error", "Cart not found!");
            return "error";
        }
        User user = cart.getUser();
        Order order = orderService.createOrder(user, cart);

        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/orders")
    public String viewOrders(@RequestParam("userId") Long userId, Model model) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        model.addAttribute("orders", orders);
        return "order-history";
    }
}

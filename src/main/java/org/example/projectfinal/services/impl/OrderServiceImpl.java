package org.example.projectfinal.services;

import org.example.projectfinal.entity.*;
import org.example.projectfinal.enums.OrderStatus;
import org.example.projectfinal.repository.OrderRepository;
import org.example.projectfinal.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Tạo đơn hàng từ giỏ hàng
    public Order createOrder(User user, Cart cart) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(calculateTotalPrice(cart));

        return orderRepository.save(order);
    }

    // Tính tổng giá trị đơn hàng từ giỏ hàng
    private double calculateTotalPrice(Cart cart) {
        double totalPrice = 0;
        for (CartItem item : cart.getItems()) {
            totalPrice += item.getQuantity() * item.getProduct().getPrice();
        }
        return totalPrice;
    }

    // Lấy danh sách đơn hàng của người dùng
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}

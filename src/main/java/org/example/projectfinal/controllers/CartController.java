package org.example.projectfinal.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.projectfinal.dto.UserDTO;
import org.example.projectfinal.dto.UserSession;
import org.example.projectfinal.entity.Cart;
import org.example.projectfinal.entity.User;
import org.example.projectfinal.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private boolean checkSession(HttpSession session) {
        Object user = session.getAttribute("user");
        return user != null;
    }

    @Autowired
    private CartService cartService;

    @GetMapping()
    public String viewCart(Model model, HttpSession session) {
        if (!checkSession(session)) return "login";

        Long userId = ((User) session.getAttribute("user")).getId();
        Cart cart = cartService.getCartByUserId(userId);
        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", cartService.calculateTotalPrice(cart));
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(HttpSession session, @RequestParam Long productId, @RequestParam int quantity) {
        Object user = session.getAttribute("user");
        if (user == null) return "login";

        Long userId = ((User) session.getAttribute("user")).getId();
        cartService.addProductToCart(userId, productId, quantity);
        return "redirect:/";
    }
    @PostMapping("/update")
    public String update(HttpSession session, @RequestParam Long productId, @RequestParam int quantity) {
        Object user = session.getAttribute("user");
        if (user == null) return "login";

        Long userId = ((User) session.getAttribute("user")).getId();
        cartService.addProductToCart(userId, productId, quantity);
        return "redirect:/cart?userId="+userId;
    }

    @PostMapping("/remove")
    public String removeFromCart(HttpSession session, @RequestParam Long productId) {
        if (!checkSession(session)) return "login";

        Long userId = ((User) session.getAttribute("user")).getId();
        cartService.removeProductFromCart(userId, productId);
        return "redirect:/cart?userId=" + userId;
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        if (!checkSession(session)) return "login";

        Long userId = ((User) session.getAttribute("user")).getId();
        cartService.clearCart(userId);
        return "redirect:/cart?userId=" + userId;
    }

}

package org.example.projectfinal.controllers;


import jakarta.servlet.http.HttpSession;
import org.example.projectfinal.dto.UserDTO;
import org.example.projectfinal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password, HttpSession session) {
        boolean isAuthenticated = userService.login(userName, password, session);
        if (isAuthenticated) {
            return "redirect:/";
        }
        return "error";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO, HttpSession session) {
        String username = userDTO.getUserName();
        String password = userDTO.getPassword();
        String result = userService.register(username, password, session);
        if (result.equals("Đăng ký thành công")) {
            return "redirect:/";
        }
        return "error";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}

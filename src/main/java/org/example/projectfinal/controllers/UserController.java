package org.example.projectfinal.controllers;


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
        return "login"; // Trang login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password) {
        boolean isAuthenticated = userService.login(userName, password);
        if (isAuthenticated) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUserName();
        String password = userDTO.getPassword();
        String result = userService.register(username, password);
        if (result.equals("Registration successful!")) {
            return "redirect:/";
        }
        return "register";
    }

}

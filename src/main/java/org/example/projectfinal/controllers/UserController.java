package org.example.projectfinal.controllers;


import jakarta.servlet.http.HttpSession;
import org.example.projectfinal.dto.UserDTO;
import org.example.projectfinal.entity.User;
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

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/forgot")
    public String forgot(@RequestParam String username){
        return "forgot";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password, HttpSession session) {
        boolean isAuthenticated = userService.login(userName, password, session);
        if (isAuthenticated) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, HttpSession session) {
        boolean result = userService.register(username, password, session);
        System.out.println(result);
        if (result) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/changePassword")
    public String changePassword( RequestParam currentPassword, RequestParam newPassword, RequestParam confirmNewPassword) {


        return "login";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}

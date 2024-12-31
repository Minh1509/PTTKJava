package org.example.projectfinal.controllers;

import org.example.projectfinal.entity.Product;
import org.example.projectfinal.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String homePage(Model model) {

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "home"; // Tên của trang home.html
    }
}
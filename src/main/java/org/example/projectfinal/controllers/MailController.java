package org.example.projectfinal.controllers;

import org.example.projectfinal.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping("/mail")
    public String sendMail(){
        return "mail";
    }

    @PostMapping("/sendMail")
    public String sendEmail(@RequestParam String to, Model model) {
        try {
            model.addAttribute("to", to);
            String subject = "Change password";
            mailService.sendEmailWithTemplate(to, subject, "src/main/resources/templates/sendMail.html");
            return "Email sent successfully!";
        } catch (Exception e) {
            return ("Failed to send email: " + e.getMessage());
        }
    }
}

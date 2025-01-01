package org.example.projectfinal.services.impl;

import jakarta.mail.internet.MimeMessage;
import org.example.projectfinal.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmailWithTemplate(String to, String subject, String templatePath) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // Đọc nội dung từ file HTML
            String template = new String(Files.readAllBytes(Paths.get(templatePath)));

            // Cấu hình email
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(template, true);
            helper.setFrom("nguyenquangminhbkimbang@gmail.com");

            // Gửi email
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();  // In ra chi tiết lỗi
        }
    }

}

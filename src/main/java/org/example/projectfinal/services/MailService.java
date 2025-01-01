package org.example.projectfinal.services;

public interface MailService {
    void sendEmailWithTemplate(String to, String subject, String templatePath) ;
}

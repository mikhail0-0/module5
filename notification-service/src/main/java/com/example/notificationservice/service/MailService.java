package com.example.notificationservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    final JavaMailSender emailSender;

    public MailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
    public void send(SimpleMailMessage message) {
        emailSender.send(message);
    }
}

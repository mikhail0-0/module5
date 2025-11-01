package com.example.notificationservice.controller;

import com.example.notificationservice.dto.SendEmailDto;
import com.example.notificationservice.service.MailService;
import org.apache.http.HttpException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mail")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService){
        this.mailService = mailService;
    }

    @PostMapping
    public void sendMail(@RequestBody SendEmailDto dto){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(dto.getFromEmail());
        mailMessage.setSubject(dto.getSubject());
        mailMessage.setTo(dto.getToEmail());
        mailMessage.setText(dto.getMessage());

        this.mailService.send(mailMessage);
    }
}

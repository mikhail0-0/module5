package com.example.notificationservice.dto;

import lombok.Data;

@Data
public class SendEmailDto {
    String fromEmail;
    String toEmail;
    String subject;
    String message;
}

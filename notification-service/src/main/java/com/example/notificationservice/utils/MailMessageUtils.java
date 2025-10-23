package com.example.notificationservice.utils;

import com.example.notificationservice.dto.KafkaDto;
import org.springframework.mail.SimpleMailMessage;

public class MailMessageUtils {
    static final String serviceEmail = "notification@service.com";
    static class MessageDiff{
        private String subject;
        private String message;

        MessageDiff(KafkaDto.EOperationType operation){
            switch (operation){
                case USER_CREATION -> {
                    subject = "Создание аккаунта";
                    message = "Здравствуйте! Ваш аккаунт на сайте был успешно создан";
                }
                case USER_DELETION -> {
                    subject = "Удаление аккаунта";
                    message = "Здравствуйте! Ваш аккаунт был удалён";
                }
            }
        }
    }

    public static SimpleMailMessage createMessage(KafkaDto dto){
        MessageDiff diff = new MessageDiff(dto.getOperation());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(serviceEmail);
        mailMessage.setSubject(diff.subject);
        mailMessage.setTo(dto.getEmail());
        mailMessage.setText(diff.message);
        return mailMessage;
    }
}

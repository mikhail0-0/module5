package com.example.notificationservice.service;

import com.example.notificationservice.dto.KafkaDto;
import com.example.notificationservice.utils.MailMessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.io.StringReader;

@Service
public class KafkaConsumerService {
    private final MailService mailService;

    public KafkaConsumerService(MailService mailService){
        this.mailService = mailService;
    }

    @KafkaListener(topics = "my_topic", groupId = "my-group")
    public void consumerMessage(String kafkaMessage){
        System.out.println("Message was received from kafka: " + kafkaMessage);
        StringReader reader = new StringReader(kafkaMessage);
        ObjectMapper mapper = new ObjectMapper();

        try{
            KafkaDto dto = mapper.readValue(reader, KafkaDto.class);
            SimpleMailMessage mailMessage = MailMessageUtils.createMessage(dto);
            mailService.send(mailMessage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

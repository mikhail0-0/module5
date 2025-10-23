package com.example.userservice.service;

import com.example.userservice.dto.KafkaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.StringWriter;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendKafkaDto(KafkaDto dto) throws Exception{
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, dto);
        String message = writer.toString();

        kafkaTemplate.send("my_topic", message);
        System.out.println("Message was send to kafka: " + message);
    }
}

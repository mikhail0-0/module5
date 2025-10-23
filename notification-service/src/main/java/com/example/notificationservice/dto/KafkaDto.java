package com.example.notificationservice.dto;

import lombok.Data;

@Data
public class KafkaDto {
    public enum EOperationType{
        USER_CREATION,
        USER_DELETION
    }

    String email;
    EOperationType operation;
}

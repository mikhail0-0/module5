package com.example.notificationservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    Long id;
    String name;
    String email;
    Integer age;
    Date createdAt;
}

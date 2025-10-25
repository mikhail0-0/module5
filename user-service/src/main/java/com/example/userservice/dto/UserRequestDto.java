package com.example.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.hateoas.Link;

import java.util.Date;

@Schema(description="Данные для создания пользователя")
@Data
public class UserRequestDto {
    @Schema(description="имя", example = "user")
    String name;
    @Schema(description="адрес электронной почты", example = "user@mail.com")
    String email;
    @Schema(description="возраст", example = "33")
    Integer age;
}

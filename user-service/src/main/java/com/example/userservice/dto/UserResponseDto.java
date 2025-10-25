package com.example.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.Date;

@Schema(description="Данные пользователя")
@Data
public class UserResponseDto {
    @Schema(description="id пользователя")
    Long id;
    @Schema(description="имя", example = "user")
    String name;
    @Schema(description="адрес электронной почты", example = "user@mail.com")
    String email;
    @Schema(description="возраст", example = "33")
    Integer age;
    @Schema(description="время создания пользователя")
    Date createdAt;

    @Schema(description="ссылка на пользователя", example = "http://localhost:8080/users/0")
    private String link;

    public void setLink(Class<?> controllerClass){
        this.link = WebMvcLinkBuilder.linkTo(controllerClass).slash(this.getId()).withSelfRel().getHref();
    }
}

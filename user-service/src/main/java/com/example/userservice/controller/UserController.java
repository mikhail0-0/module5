package com.example.userservice.controller;

import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Tag(name="Get-методы")
    @Operation(summary="Получение списка всех пользователей")
    @GetMapping
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @Tag(name="Get-методы")
    @Operation(summary="Получение пользователя")
    @GetMapping("/{id}")
    public UserResponseDto getUserById(
            @Parameter(description="id пользователя, которого необходимо получить")
            @PathVariable Long id
    ) {
        return userService.getUserById(id);
    }

    @Tag(name="Post-методы")
    @Operation(summary="Создание пользователя")
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto requestDto){
        UserResponseDto responseDto = userService.createUser(requestDto);
        responseDto.setLink(UserController.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Tag(name="Delete-методы")
    @Operation(summary="Удаление пользователя")
    @DeleteMapping("/{id}")
    public void deleteUser(
        @Parameter(description="id пользователя, которого необходимо удалить")
        @PathVariable Long id
    ){
        userService.deleteUser(id);
    }
}

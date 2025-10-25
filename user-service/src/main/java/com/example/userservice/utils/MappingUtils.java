package com.example.userservice.utils;

import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.entity.User;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public UserResponseDto mapToUserDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    public User mapToUser(UserRequestDto dto){
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        return user;
    }
}

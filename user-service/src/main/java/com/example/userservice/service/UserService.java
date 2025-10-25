package com.example.userservice.service;

import com.example.userservice.controller.UserController;
import com.example.userservice.dto.KafkaDto;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.utils.MappingUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MappingUtils mappingUtils;
    private final KafkaProducerService kafkaProducerService;

    public UserService(UserRepository userRepository, MappingUtils mappingUtils, KafkaProducerService kafkaProducerService){
        this.userRepository = userRepository;
        this.mappingUtils = mappingUtils;
        this.kafkaProducerService = kafkaProducerService;
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(mappingUtils::mapToUserDto).peek(u -> u.setLink(UserController.class)).collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return null;
        }
        return mappingUtils.mapToUserDto(user);
    }

    public UserResponseDto createUser(UserRequestDto dto) {
        User user = mappingUtils.mapToUser(dto);
        UserResponseDto userDto = mappingUtils.mapToUserDto(userRepository.save(user));

        KafkaDto kafkaDto = new KafkaDto();
        kafkaDto.setEmail(user.getEmail());
        kafkaDto.setOperation(KafkaDto.EOperationType.USER_CREATION);

        try {
            kafkaProducerService.sendKafkaDto(kafkaDto);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return userDto;
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            System.out.println("User doesn't exist");
            return;
        }

        userRepository.delete(user);
        KafkaDto kafkaDto = new KafkaDto();
        kafkaDto.setEmail(user.getEmail());
        kafkaDto.setOperation(KafkaDto.EOperationType.USER_DELETION);

        try {
            kafkaProducerService.sendKafkaDto(kafkaDto);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

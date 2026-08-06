package com.ordermanagement.userservice.mapper;

import com.ordermanagement.userservice.dto.UserRequestDto;
import com.ordermanagement.userservice.dto.UserResponseDto;
import com.ordermanagement.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto userRequestDto) {

        User user = new User();

        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setGender(userRequestDto.getGender());
        user.setDateOfBirth(userRequestDto.getDateOfBirth());

        return user;
    }

    public UserResponseDto toResponseDto(User user) {

        UserResponseDto response = new UserResponseDto();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setGender(user.getGender());
        response.setDateOfBirth(user.getDateOfBirth());
        response.setStatus(user.getStatus());
        return response;
    }
}

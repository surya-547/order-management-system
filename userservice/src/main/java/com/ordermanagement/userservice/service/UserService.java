package com.ordermanagement.userservice.service;

import com.ordermanagement.userservice.dto.UserRequestDto;
import com.ordermanagement.userservice.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface UserService {

    public UserResponseDto registerUser(UserRequestDto userRequestDto);

    public UserResponseDto getUserById(Long id);

    public Page<UserResponseDto> getAllUsers(Pageable pageable);

    public void deleteUserById(Long id);

    public UserResponseDto updateUserById(Long id,  UserRequestDto userRequestDto);
}

package com.ordermanagement.userservice.serviceImpl;

import com.ordermanagement.userservice.dto.UserRequestDto;
import com.ordermanagement.userservice.dto.UserResponseDto;
import com.ordermanagement.userservice.entity.User;
import com.ordermanagement.userservice.exception.UserAlreadyExistsException;
import com.ordermanagement.userservice.exception.UserNotFoundException;
import com.ordermanagement.userservice.mapper.UserMapper;
import com.ordermanagement.userservice.repository.UserRespository;
import com.ordermanagement.userservice.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRespository userRespository;

    public UserServiceImpl(UserMapper userMapper, UserRespository userRespository) {
        this.userMapper = userMapper;
        this.userRespository = userRespository;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {

        Optional<User> existedUser = userRespository.findByEmail(userRequestDto.getEmail());

        if(existedUser.isPresent()){

            throw new UserAlreadyExistsException("User already exists with email: " +userRequestDto.getEmail());
        }
        User user = userMapper.toEntity(userRequestDto);

        user.setStatus(User.Status.ACTIVE);

        User savedUser = userRespository.save(user);

        return userMapper.toResponseDto(savedUser);

    }

    @Override
    public UserResponseDto getUserById(Long id) {

        User userById =  userRespository.findById(id)
                .orElseThrow(()->new UserNotFoundException(" User not found with id:" +id));

        return userMapper.toResponseDto(userById);
    }

    @Override
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return userRespository.findAll(pageable)
                .map(userMapper::toResponseDto);
    }


    @Override
    public void deleteUserById(Long id) {

        User userById = userRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userRespository.delete(userById);
    }

    @Override
    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto) {

        User user = userRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setGender(userRequestDto.getGender());
        user.setDateOfBirth(userRequestDto.getDateOfBirth());

        User savedUser =  userRespository.save(user);

        return userMapper.toResponseDto(savedUser);
    }
}

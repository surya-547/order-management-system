package com.ordermanagement.userservice.controller;

import com.ordermanagement.userservice.dto.UserRequestDto;
import com.ordermanagement.userservice.dto.UserResponseDto;
import com.ordermanagement.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody  UserRequestDto userRequestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userRequestDto));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @GetMapping("/allUsers")
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(@RequestParam(defaultValue="0") int page,
                                                              @RequestParam(defaultValue = "5") int size
                                                              ){
        Pageable pageable = PageRequest.of(page, size);
        return  ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers(pageable));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){

        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateUserById(@PathVariable Long id, @Valid @RequestBody UserRequestDto userRequestDto){

        UserResponseDto userResponseDto = userService.updateUserById(id, userRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }
}

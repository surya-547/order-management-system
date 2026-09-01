package com.ordermanagement.userservice.exception;


import com.ordermanagement.userservice.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto>  handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){

        ErrorResponseDto errorResponse = new ErrorResponseDto();

        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorResponse.setMessage(userAlreadyExistsException.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException userNotFoundException){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFoundException.getMessage());
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserIdNotFoundException(UserIdNotFoundException userIdNotFoundException){

        ErrorResponseDto errorResponse = new ErrorResponseDto();

        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setMessage(userIdNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, String> validationErrors = new HashMap<>();

        ErrorResponseDto errorResponse = new ErrorResponseDto();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );
        }

        errorResponse.setValidationErrors(validationErrors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}

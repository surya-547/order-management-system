package com.ordermanagement.userservice.dto;

import com.ordermanagement.userservice.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "First name is required ")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Last name is required ")
    @Size(max = 50)
    private String lastName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required ")
    private String email;

    @NotBlank

    @Pattern(regexp = "^\\d{10}$",
            message = "Phone number must be a valid 10-digit Indian mobile number"
            )
    private String phoneNumber;

    @NotBlank(message = " password is required")
    @Pattern(
            regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$")
    private String password;

    private User.Gender gender;

    private LocalDate dateOfBirth;

}

package com.project.creditcardpayment.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    @NotNull
    @NotEmpty(message = "First Name should not be empty!")
    private String firstName;

    @NotNull
    @NotEmpty(message = "Last Name should not be empty!")
    private String lastName;

    @NotNull
    @NotEmpty(message = "Email should not be empty!")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String emailId;

    @NotNull
    @NotEmpty(message = "Password should not be empty!")
    private String password;


}

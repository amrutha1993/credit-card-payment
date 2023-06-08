package com.project.creditcardpayment.auth;


import com.project.creditcardpayment.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

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

    private Role role;
}

package com.project.creditcardpayment.service;

import com.project.creditcardpayment.dto.LoginDto;
import com.project.creditcardpayment.dto.RegistrationDto;
import com.project.creditcardpayment.exception.InvalidCredentialsException;
import com.project.creditcardpayment.exception.NotFoundException;
import com.project.creditcardpayment.exception.UserAlreadyExistException;

public interface UserService {

    // Register User Details
    void registerUser(RegistrationDto registrationDto) throws UserAlreadyExistException;

    //Login
    boolean userLogin(LoginDto loginDto) throws NotFoundException, InvalidCredentialsException;

}

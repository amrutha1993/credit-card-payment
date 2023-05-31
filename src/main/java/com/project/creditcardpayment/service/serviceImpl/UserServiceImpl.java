package com.project.creditcardpayment.service.serviceImpl;

import com.project.creditcardpayment.dto.LoginDto;
import com.project.creditcardpayment.dto.RegistrationDto;
import com.project.creditcardpayment.entity.User;
import com.project.creditcardpayment.exception.InvalidCredentialsException;
import com.project.creditcardpayment.exception.NotFoundException;
import com.project.creditcardpayment.exception.UserAlreadyExistException;
import com.project.creditcardpayment.repository.UserRepository;
import com.project.creditcardpayment.service.EmailService;
import com.project.creditcardpayment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

import static com.project.creditcardpayment.constants.SetConstants.INVALID_CREDENTIALS;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerUser(RegistrationDto registrationDto) throws UserAlreadyExistException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmailId(registrationDto.getEmailId()));
        if (user.isPresent()) {
            throw new UserAlreadyExistException("Email ID is already registered! Please try with another id.");
        }
        saveUserDetails(registrationDto);
        emailService.sendEmail(registrationDto.getEmailId(), "You are registered successfully to payment application. Please login to continue.",
                "Registration Successful", null);
    }


    @Override
    public boolean userLogin(LoginDto loginDto) throws NotFoundException, InvalidCredentialsException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmailId(loginDto.getEmailId()));
        if (!user.isPresent()) {
            throw new NotFoundException("User not found for this Email ID!");
        }
        if (encodePassword(loginDto.getPassword().getBytes()).equals(user.get().getPassword())) {
            return true;
        } else {
            throw new InvalidCredentialsException(INVALID_CREDENTIALS);
        }
    }

    private User saveUserDetails(RegistrationDto registrationDto) {
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmailId(registrationDto.getEmailId());
        user.setPassword(encodePassword(registrationDto.getPassword().getBytes()));
        userRepository.save(user);
        return user;
    }

    private String encodePassword(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

}

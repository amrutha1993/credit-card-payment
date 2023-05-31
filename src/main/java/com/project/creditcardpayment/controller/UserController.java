package com.project.creditcardpayment.controller;

import com.project.creditcardpayment.dto.LoginDto;
import com.project.creditcardpayment.dto.RegistrationDto;
import com.project.creditcardpayment.exception.InvalidCredentialsException;
import com.project.creditcardpayment.exception.NotFoundException;
import com.project.creditcardpayment.exception.UserAlreadyExistException;
import com.project.creditcardpayment.repository.UserRepository;
import com.project.creditcardpayment.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/user")
@Log4j2
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationDto user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok().body("User registration completed successfully!");

        } catch (UserAlreadyExistException e) {
            log.error(e);
            return ResponseEntity.badRequest()
                    .body("User already registered.");
        }

    }

    @GetMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginDto user) {
        try {
            userService.userLogin(user);
            return ResponseEntity.ok().body("User logged in!");
        } catch (NotFoundException e) {
            log.error(e);
            return ResponseEntity.badRequest()
                    .body("User not found for this Email ID!");
        } catch (InvalidCredentialsException e) {
            log.error(e);
            return ResponseEntity.badRequest()
                    .body("Invalid ID or password!");
        }
    }
}

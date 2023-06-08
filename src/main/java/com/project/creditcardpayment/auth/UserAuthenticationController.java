package com.project.creditcardpayment.auth;

import com.project.creditcardpayment.config.LogoutService;
import com.project.creditcardpayment.exception.NotFoundException;
import com.project.creditcardpayment.exception.UserAlreadyExistException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Log4j2
public class UserAuthenticationController {

    private final AuthenticationService service;
    private final LogoutService logoutService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {
        try {
            service.register(request);
            return ResponseEntity.ok().body("User registration completed successfully!");

        } catch (UserAlreadyExistException e) {
            log.error(e);
            return ResponseEntity.badRequest()
                    .body("User is already registered.");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> loginAuthentication(@RequestBody @Valid AuthenticationRequest request) {
        try {
            return ResponseEntity.ok().body(service.getLoginToken(request));
        } catch (NotFoundException e) {
            log.error(e);
            return ResponseEntity.badRequest()
                    .body("Invalid Credentials!");
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}

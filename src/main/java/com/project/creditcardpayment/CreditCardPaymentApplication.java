package com.project.creditcardpayment;

import com.project.creditcardpayment.auth.AuthenticationService;
import com.project.creditcardpayment.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.project.creditcardpayment.user.Role.ADMIN;

@SpringBootApplication
public class CreditCardPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditCardPaymentApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstName("Admin")
                    .lastName("Admin")
                    .emailId("admin@gmail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

        };
    }

}

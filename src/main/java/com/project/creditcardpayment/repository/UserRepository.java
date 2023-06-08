package com.project.creditcardpayment.repository;

import com.project.creditcardpayment.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailId(String email);

}

package com.project.creditcardpayment.repository;

import com.project.creditcardpayment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailId(String emailId);
}

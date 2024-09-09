package com.example.Authorization.repository;

import com.example.Authorization.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Integer> {
    @Override
    Optional<User> findById(Integer integer);

    Optional<User> findUserByEmail(String email);
}

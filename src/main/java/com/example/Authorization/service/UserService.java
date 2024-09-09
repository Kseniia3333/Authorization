package com.example.Authorization.service;

import com.example.Authorization.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Integer id);
    List<User> getAllUsers();
    boolean deleteUser(Integer id);
}

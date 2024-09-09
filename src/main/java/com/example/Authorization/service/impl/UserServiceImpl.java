package com.example.Authorization.service.impl;

import com.example.Authorization.entity.User;
import com.example.Authorization.exception.UserNotFoundException;
import com.example.Authorization.repository.UserRepository;
import com.example.Authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    /**
     * @param id id пользователя
     * @return пользователь
     */
    @Override
    public User getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        log.info(String.valueOf(user));
        return user;
    }

    /**
     * @return список пользователей
     */

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = userRepository.findAll();
        log.info(listUsers.toString());
        return listUsers;

    }

    @Override
    public boolean deleteUser(Integer id) {
        log.info("запустился метод удаления пользователя");
       User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
           userRepository.delete(user);
        return false;
    }
    }


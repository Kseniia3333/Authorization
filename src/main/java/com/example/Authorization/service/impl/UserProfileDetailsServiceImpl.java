package com.example.Authorization.service.impl;

import com.example.Authorization.controller.UserController;
import com.example.Authorization.dto.NewPasswordDto;
import com.example.Authorization.dto.UpdateUserDto;
import com.example.Authorization.entity.Image;
import com.example.Authorization.entity.User;
import com.example.Authorization.exception.UserNotFoundException;
import com.example.Authorization.exception.WrongPasswordException;
import com.example.Authorization.mapper.UserMapper;
import com.example.Authorization.repository.UserRepository;
import com.example.Authorization.service.PhotoService;
import com.example.Authorization.service.UserProfileDetailsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserProfileDetailsServiceImpl implements UserProfileDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final PhotoService photoService;


    Logger log = LoggerFactory.getLogger(UserController.class);


    @Override
    public void setPassword(NewPasswordDto newPassword, Authentication authentication) {
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }
        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);

        log.info("Вызван метод сервиса для обновления пароля пользователя с ID: {}", user.getId());

    }

    @Override
    public User getAuthUserInfo(Authentication authentication) {
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()).orElseThrow(UserNotFoundException::new);

        log.info("Вызван метод сервиса для получения информации о пользователе с ID: {}", user.getId());

        return user;
    }

    @Override
    public UpdateUserDto updateAuthUserInfo(UpdateUserDto updateUser, Authentication authentication) {
        User oldUser = userRepository.findUserByEmail(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()).orElseThrow(UserNotFoundException::new);

        oldUser.setFirstName(updateUser.getFirstName());
        oldUser.setSurName(updateUser.getSurName());
        oldUser.setLastName(updateUser.getLastName());
        oldUser.setPhone(updateUser.getPhone());

        userRepository.save(oldUser);
        UpdateUserDto newUser = userMapper.userToUpdateUserDto(oldUser);

        log.info("Вызван метод сервиса для обновления информации о пользователе с ID: {}", oldUser.getId());

        return newUser;
    }

    @Override
    public boolean updatePhoto(MultipartFile avatar, Authentication authentication) throws IOException {
        User user = userRepository.findUserByEmail(authentication.getName())
                .orElseThrow();
        Image photo;
        photo = photoService.saveImageFile(avatar);
        user.setPhoto(photo);
        userRepository.saveAndFlush(user);
        return true;
    }

    @Override
    public String getPhotoByUserId(Integer userId) {
        User user = new User();
        String filePath = user.getPhoto().getFilePath();

        log.info("Вызван метод сервиса для получения фото по ID: {}", user.getId());
        return filePath;
    }
}






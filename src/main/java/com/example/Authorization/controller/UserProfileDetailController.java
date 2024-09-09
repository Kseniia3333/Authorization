package com.example.Authorization.controller;

import com.example.Authorization.dto.NewPasswordDto;
import com.example.Authorization.dto.UpdateUserDto;
import com.example.Authorization.dto.UserDto;
import com.example.Authorization.entity.User;
import com.example.Authorization.mapper.UserMapper;
import com.example.Authorization.service.UserProfileDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user_details")
public class UserProfileDetailController {
    private final UserProfileDetailsService userProfileDetailsService;
    private final UserMapper userMapper;


    @PostMapping("/set_password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity setPassword(@RequestBody NewPasswordDto newPassword, Authentication authentication) {

        log.info("Вызван метод контроллера для обновления пароля пользователя с login: {}", authentication.getName());
        userProfileDetailsService.setPassword(newPassword, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public UserDto getAuthUserInfo(Authentication authentication) {

        log.info("Вызван метод контроллера для получения информации об авторизованном пользователе с login: {}", authentication.getName());
        User user = userProfileDetailsService.getAuthUserInfo(authentication);
        return userMapper.userToUserDto(user);

    }

    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateAuthUserInfo(@RequestBody UpdateUserDto updateUser, Authentication authentication) {

        log.info("Вызван метод контроллера для изменения информации об авторизованном пользователе с login: {}", authentication.getName());

        return ResponseEntity.ok(userProfileDetailsService.updateAuthUserInfo(updateUser, authentication));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<Void> updateAvatar(@NotNull Authentication authentication, @Valid @RequestPart(value = "image",
            required = false) MultipartFile image) throws IOException {

        log.info("Вызван метод контроллера для обновления аватара");

        if (userProfileDetailsService.updatePhoto(image, authentication)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }

    @GetMapping("/image/{id}")
    public String getImageByUserId(@PathVariable Integer userId) {

        log.info("Вызван метод контроллера для получения аватара пользователя с ID: {}", userId);

        return userProfileDetailsService.getPhotoByUserId(userId);
    }
}


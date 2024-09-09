package com.example.Authorization.service;

import com.example.Authorization.dto.NewPasswordDto;
import com.example.Authorization.dto.UpdateUserDto;
import com.example.Authorization.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserProfileDetailsService {
    void setPassword(NewPasswordDto newPassword, Authentication authentication);

    User getAuthUserInfo(Authentication authentication);

    UpdateUserDto updateAuthUserInfo(UpdateUserDto updateUser, Authentication authentication);

    boolean updatePhoto(MultipartFile avatar, Authentication authentication) throws IOException;

    String getPhotoByUserId(Integer userId);

}

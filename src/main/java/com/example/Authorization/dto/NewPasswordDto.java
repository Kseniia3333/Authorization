package com.example.Authorization.dto;

import lombok.Data;

import javax.validation.constraints.Size;


@Data
public class NewPasswordDto {
    private String currentPassword;

    private String newPassword;

    public NewPasswordDto currentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        return this;
    }
}


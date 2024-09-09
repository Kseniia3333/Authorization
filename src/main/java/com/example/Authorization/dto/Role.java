package com.example.Authorization.dto;

public enum Role {
    ADMIN("ADMIN");
    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

}

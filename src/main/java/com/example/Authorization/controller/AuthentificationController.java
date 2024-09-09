package com.example.Authorization.controller;

import com.example.Authorization.dto.Login;
import com.example.Authorization.service.AuthentificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthentificationController {
    private final AuthentificationService authentificationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        if (authentificationService.login(login.getUsername(), login.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

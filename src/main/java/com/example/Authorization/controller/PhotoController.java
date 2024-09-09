package com.example.Authorization.controller;

import com.example.Authorization.service.PhotoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;


    @GetMapping("/image/{id}")
    public void transferImageToResponse(@PathVariable Integer id, HttpServletResponse response) {
        try {
            photoService.transferImageToResponse(id, response);
        } catch (Exception e) {
            log.error("Ошибка при передаче изображения в ответ", e);
            throw new RuntimeException("Ошибка при передаче изображения в ответ", e);
        }
    }
}

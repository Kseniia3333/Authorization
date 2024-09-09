package com.example.Authorization.service;

import com.example.Authorization.entity.Image;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    void transferImageToResponse(Integer id, HttpServletResponse response);

    /**
     * @param imageFile файл с изображением
     * @return файл с изображением в директории
     * @throws IOException
     */
    Image saveImageFile (MultipartFile imageFile) throws IOException;


}



package com.example.Authorization.repository;

import com.example.Authorization.entity.Image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByFilePath(String string);
}

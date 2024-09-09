package com.example.Authorization.entity;

import jakarta.persistence.*;
import lombok.*;

import java.nio.file.Path;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHOTO_ID", nullable = false)
    private Integer id;

    @Column(name = "FILE_PATH", nullable = false)
    private String filePath;

    @Column(name = "FILE_EXTENSION", nullable = false)
    private String fileExtension;

    @Column(name = "FILE_SIZE", nullable = false)
    private long fileSize;

    @Column(name = "MEDIA_TYPE", nullable = false)
    private String mediaType;

    public Path getPath() {
        return Path.of(this.filePath);
    }

    public String getUrl() {
        return String.format("/%s/%d", getPath().getParent(), getId());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Image image = (Image) object;
        return fileSize == image.fileSize && Objects.equals(id, image.id) && Objects.equals(filePath, image.filePath)
                && Objects.equals(fileExtension, image.fileExtension) && Objects.equals(mediaType, image.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filePath, fileExtension, fileSize, mediaType);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}


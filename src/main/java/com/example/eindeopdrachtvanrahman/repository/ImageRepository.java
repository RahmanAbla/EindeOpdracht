package com.example.eindeopdrachtvanrahman.repository;

import com.example.eindeopdrachtvanrahman.models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageData,Long> {
    ImageData findByNameOfImage(String fileName);
}

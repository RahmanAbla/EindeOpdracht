package com.example.eindeopdrachtvanrahman.controllers;

import com.example.eindeopdrachtvanrahman.Services.CarMechanicService;
import com.example.eindeopdrachtvanrahman.Services.ImageDataService;
import com.example.eindeopdrachtvanrahman.dto.ImageDataDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/image")
public class ImageDataController {
    private final ImageDataService imageDataService;
    private final CarMechanicService carMechanicService;

    public ImageDataController(ImageDataService imageDataService, CarMechanicService carMechanicService) {
        this.imageDataService =imageDataService;
        this.carMechanicService = carMechanicService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> addImage(@PathVariable("id") Long id , @RequestParam(value = "image", required = false) MultipartFile file, ImageDataDto dto) throws IOException {
        String uploadImage = String.valueOf(imageDataService.uploadImage(id, file, dto));

        return ResponseEntity.ok().body(uploadImage);
    }


    @GetMapping("")
    public List<ImageDataDto> getAllImageData(){

        List<ImageDataDto> dtoOutputs = imageDataService.getAllImageData();

        return dtoOutputs;
    }

    @GetMapping("/{id}")
    public ImageDataDto getImageDataById(@PathVariable("id") Long id){

        ImageDataDto dataDtoOutput = imageDataService.getImageDataById(id);

        return dataDtoOutput;
    }

    @GetMapping("/Download/{fileName}")
    @Transactional
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        return imageDataService.singleFileDownload(fileName, request);
    }


}

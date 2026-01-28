package com.thirdeye3.video.manager.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadDto {
    private String name;        
    private String description;
    private MultipartFile file;
}
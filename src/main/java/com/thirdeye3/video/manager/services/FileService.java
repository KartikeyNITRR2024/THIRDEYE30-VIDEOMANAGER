package com.thirdeye3.video.manager.services;

import org.springframework.core.io.Resource;
import com.thirdeye3.video.manager.dtos.FileResponseDto;
import com.thirdeye3.video.manager.dtos.FileUploadDto;

public interface FileService {
    FileResponseDto uploadFile(FileUploadDto uploadDto);
    Resource downloadFile(String s3Key);
    FileResponseDto getFileDetails(String s3Key);
}
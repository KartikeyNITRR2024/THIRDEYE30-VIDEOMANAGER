package com.thirdeye3.video.manager.services;

import org.springframework.core.io.Resource;
import com.thirdeye3.video.manager.dtos.FileResponseDto;
import com.thirdeye3.video.manager.dtos.FileUploadDto;

public interface FileService {
    Resource downloadFile(String s3Key);
    byte[] downloadFileContent(String s3Key);
    FileResponseDto getFileDetails(String s3Key);
	FileResponseDto uploadFile(FileUploadDto uploadDto);
	FileResponseDto uploadAudioFile(Long newsId, FileUploadDto uploadDto);
}
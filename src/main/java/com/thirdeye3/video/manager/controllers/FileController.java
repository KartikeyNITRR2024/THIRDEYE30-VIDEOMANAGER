package com.thirdeye3.video.manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import com.thirdeye3.video.manager.dtos.FileResponseDto;
import com.thirdeye3.video.manager.dtos.FileUploadDto;
import com.thirdeye3.video.manager.dtos.Response;
import com.thirdeye3.video.manager.services.FileService;

import java.io.IOException;
import java.net.URLConnection;

@RestController
@RequestMapping("/vm/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<FileResponseDto> uploadFile(@ModelAttribute FileUploadDto uploadDto) {
        return new Response<>(true, 0, null, fileService.uploadFile(uploadDto));
    }

    @GetMapping("/view")
    public ResponseEntity<Resource> viewFile(@RequestParam("key") String s3Key) {
        FileResponseDto metadata = fileService.getFileDetails(s3Key);
        //Resource resource = fileService.downloadFile(s3Key);
        
        byte[] fileData = fileService.downloadFileContent(s3Key);
        ByteArrayResource resource = new ByteArrayResource(fileData);
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(metadata.getFileType()))
                .contentLength(metadata.getSize()) 
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + metadata.getName() + "\"")
                .body(resource);
    }

    @GetMapping("/info")
    public Response<FileResponseDto> getFileInfo(@RequestParam("key") String s3Key) {
        return new Response<>(true, 0, null, fileService.getFileDetails(s3Key));
    }
}
package com.thirdeye3.video.manager.services.impl;

import io.awspring.cloud.s3.S3Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.thirdeye3.video.manager.dtos.FileResponseDto;
import com.thirdeye3.video.manager.dtos.FileUploadDto;
import com.thirdeye3.video.manager.entities.FileMetadata;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;
import com.thirdeye3.video.manager.repositories.FileRepository;
import com.thirdeye3.video.manager.services.FileService;
import com.thirdeye3.video.manager.services.NewsService;
import com.thirdeye3.video.manager.services.VideoService;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private S3Template s3Template;

    @Autowired
    private FileRepository fileRepository;

    @Value("${thirdeye.bucket.name}")
    private String bucketName;
    
    @Value("${thirdeye.files.url.starter}") 
    private String urlStarter;
    
    @Autowired
    private NewsService newsService;

    @Override
    public FileResponseDto uploadFile(FileUploadDto uploadDto) {

        log.info("File upload request received | name={} | size={} | type={}",
                uploadDto.getName(),
                uploadDto.getFile().getSize(),
                uploadDto.getFile().getContentType());

        try {
            String originalName = uploadDto.getFile().getOriginalFilename();
            String extension = "";

            log.info("Original filename: {}", originalName);

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }

            log.info("Detected file extension: {}", extension);

            String folderName = "default";
            if (uploadDto.getName() != null && !uploadDto.getName().trim().isEmpty()) {
                folderName = uploadDto.getName().trim().replaceAll("[^a-zA-Z0-9\\-_]", "_");
            }

            log.info("Resolved S3 folder name: {}", folderName);

            String s3Key = folderName + "/" + UUID.randomUUID().toString() + extension;

            log.info("Generated S3 key: {}", s3Key);

            s3Template.upload(bucketName, s3Key, uploadDto.getFile().getInputStream());

            log.info("File successfully uploaded to S3 | bucket={} | key={}", bucketName, s3Key);

            FileMetadata metadata = FileMetadata.builder()
                    .name(uploadDto.getName())
                    .description(uploadDto.getDescription())
                    .s3Key(s3Key)
                    .fileType(uploadDto.getFile().getContentType())
                    .size(uploadDto.getFile().getSize())
                    .build();

            FileMetadata savedEntity = fileRepository.save(metadata);

            log.info("File metadata saved successfully | id={} | key={}", 
                    savedEntity.getId(), savedEntity.getS3Key());

            FileResponseDto response = mapToDto(savedEntity);

            log.info("File upload process completed | id={} | url={}", 
                    response.getId(), response.getUrl());

            return response;

        } catch (IOException e) {
            log.error("File upload failed due to IO error | name={}", uploadDto.getName(), e);
            throw new ResourceNotFoundException("Failed to upload file");
        } catch (Exception e) {
            log.error("File upload failed due to unexpected error | name={}", uploadDto.getName(), e);
            throw new ResourceNotFoundException("Unexpected error occurred while uploading file");
        }
    }
    
    @Override
	public FileResponseDto uploadAudioFile(Long newsId, FileUploadDto uploadDto) {
    	log.info("File upload request received | name={} | size={} | type={}",
                uploadDto.getName(),
                uploadDto.getFile().getSize(),
                uploadDto.getFile().getContentType());

        try {
            String originalName = uploadDto.getFile().getOriginalFilename();
            String extension = "";

            log.info("Original filename: {}", originalName);

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }

            log.info("Detected file extension: {}", extension);

            String folderName = "default";
            if (uploadDto.getName() != null && !uploadDto.getName().trim().isEmpty()) {
                folderName = uploadDto.getName().trim().replaceAll("[^a-zA-Z0-9\\-_]", "_");
            }

            log.info("Resolved S3 folder name: {}", folderName);

            String s3Key = folderName + "/" + UUID.randomUUID().toString() + extension;

            log.info("Generated S3 key: {}", s3Key);

            s3Template.upload(bucketName, s3Key, uploadDto.getFile().getInputStream());

            log.info("File successfully uploaded to S3 | bucket={} | key={}", bucketName, s3Key);

            FileMetadata metadata = FileMetadata.builder()
                    .name(uploadDto.getName())
                    .description(uploadDto.getDescription())
                    .s3Key(s3Key)
                    .fileType(uploadDto.getFile().getContentType())
                    .size(uploadDto.getFile().getSize())
                    .build();

            FileMetadata savedEntity = fileRepository.save(metadata);
            newsService.linkAudioToNews(newsId, savedEntity);

            log.info("File metadata saved successfully | id={} | key={}", 
                    savedEntity.getId(), savedEntity.getS3Key());

            FileResponseDto response = mapToDto(savedEntity);

            log.info("File upload process completed | id={} | url={}", 
                    response.getId(), response.getUrl());

            return response;

        } catch (IOException e) {
            log.error("File upload failed due to IO error | name={}", uploadDto.getName(), e);
            throw new ResourceNotFoundException("Failed to upload file");
        } catch (Exception e) {
            log.error("File upload failed due to unexpected error | name={}", uploadDto.getName(), e);
            throw new ResourceNotFoundException("Unexpected error occurred while uploading file");
        }
    	
	}

    @Override
    public Resource downloadFile(String s3Key) {
        log.info("Download request received | key={}", s3Key);
        Resource resource = s3Template.download(bucketName, s3Key);
        log.info("Download successful | key={}", s3Key);
        return resource;
    }

    @Override
    public FileResponseDto getFileDetails(String s3Key) {
        log.info("Fetching file details | key={}", s3Key);

        FileMetadata entity = fileRepository.findByS3Key(s3Key)
                .orElseThrow(() -> {
                    log.warn("File not found in DB | key={}", s3Key);
                    return new ResourceNotFoundException("File not found with key: " + s3Key);
                });

        log.info("File details found | id={} | key={}", entity.getId(), entity.getS3Key());

        return mapToDto(entity);
    }

    private FileResponseDto mapToDto(FileMetadata entity) {

        log.info("Mapping FileMetadata to FileResponseDto | id={}", entity.getId());

        String viewUrl = UriComponentsBuilder.fromHttpUrl(urlStarter)
                .path("/vm/files/view")
                .queryParam("key", entity.getS3Key())
                .build()
                .toUriString();

        FileResponseDto dto = FileResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .s3Key(entity.getS3Key())
                .fileType(entity.getFileType())
                .size(entity.getSize())
                .url(viewUrl)
                .build();

        log.info("DTO mapping completed | id={} | url={}", dto.getId(), dto.getUrl());

        return dto;
    }

	
}

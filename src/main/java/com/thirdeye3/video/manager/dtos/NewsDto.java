package com.thirdeye3.video.manager.dtos;

import java.util.UUID;

import com.thirdeye3.video.manager.entities.FileMetadata;
import com.thirdeye3.video.manager.entities.Video;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class NewsDto {
    private Long newsId;
    private UUID videoId;
    private String header;
    private String content;
    private Boolean isImagePresent;
    private String imageUrl;
    private Boolean isSoundPresent;
    private String audioContent;
    private Boolean isSoundCreated;
    private Long file;
    private Video video;
}
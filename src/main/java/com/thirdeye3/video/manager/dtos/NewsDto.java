package com.thirdeye3.video.manager.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class NewsDto {
    private Long newsId;
    private UUID videoId;
    private String header;
    private String content;
    private Boolean isImagePresent;
    private String imageUrl;
}
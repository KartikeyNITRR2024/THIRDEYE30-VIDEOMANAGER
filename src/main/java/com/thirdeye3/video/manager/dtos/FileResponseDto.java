package com.thirdeye3.video.manager.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResponseDto {
    private Long id;
    private String name;
    private String description;
    private String s3Key;
    private String fileType;
    private long size;
    private String url;
}
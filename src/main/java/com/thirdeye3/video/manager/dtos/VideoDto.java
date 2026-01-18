package com.thirdeye3.video.manager.dtos;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VideoDto {
    private UUID id;
    private String name;
    private LocalDateTime createdDateTime;
    private Integer currentState;
    private LocalDateTime videoDate;
    private String note;
    private Boolean completed;
    private Boolean isGroupPresent;
    private Long groupId;
    private Boolean isAdPresent;
    private Long adId;
}
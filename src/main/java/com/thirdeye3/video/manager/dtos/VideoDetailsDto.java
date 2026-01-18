package com.thirdeye3.video.manager.dtos;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VideoDetailsDto {
    private Long id;
    private UUID videoId;
    private String introHeader;
    private String introSubHeader;
    private String outroHeader;
    private String outroSubHeader;
    private Boolean active;
    private LocalDateTime lastlyUsed;
}
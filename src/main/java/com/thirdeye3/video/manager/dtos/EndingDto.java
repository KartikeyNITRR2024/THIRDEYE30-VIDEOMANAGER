package com.thirdeye3.video.manager.dtos;

import lombok.Data;
import java.util.UUID;

@Data
public class EndingDto {
    private Long id;
    private UUID videoId;
}
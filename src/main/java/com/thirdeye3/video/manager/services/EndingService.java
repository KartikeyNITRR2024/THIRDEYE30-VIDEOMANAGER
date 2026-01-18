package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.EndingDto;
import java.util.UUID;

public interface EndingService {
    EndingDto getEnding();
    EndingDto updateEnding(UUID videoId);
}
package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.OutroVideoDto;
import java.util.List;

public interface OutroVideoService {
    OutroVideoDto createOutroVideo(OutroVideoDto dto);
    OutroVideoDto getOutroVideoById(Long id);
    List<OutroVideoDto> getAllOutroVideos();
    OutroVideoDto updateOutroVideo(Long id, OutroVideoDto dto);
    void deleteOutroVideo(Long id);
    OutroVideoDto updateActive(Long id);
    OutroVideoDto getActiveOutroVideo();
}

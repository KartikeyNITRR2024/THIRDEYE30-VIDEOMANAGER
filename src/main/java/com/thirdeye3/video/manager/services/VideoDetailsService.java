package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.VideoDetailsDto;
import java.util.List;
import java.util.UUID;

public interface VideoDetailsService {
    VideoDetailsDto createVideoDetails(VideoDetailsDto dto);
    VideoDetailsDto getVideoDetailsById(Long id);
    List<VideoDetailsDto> getAllVideoDetails();
    VideoDetailsDto updateVideoDetails(Long id, VideoDetailsDto dto);
    void deleteVideoDetails(Long id);
    VideoDetailsDto updateActive(Long id);
    VideoDetailsDto getActiveVideoDetails();
    VideoDetailsDto getVideoDetailsByVideoId(UUID videoId);
}
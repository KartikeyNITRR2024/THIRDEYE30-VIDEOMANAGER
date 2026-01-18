package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.VideoDto;
import java.util.List;
import java.util.UUID;

public interface VideoService {
    VideoDto createVideo(VideoDto videoDto);
    VideoDto getVideoById(UUID id);
    List<VideoDto> getAllVideos();
    void deleteVideo(UUID id);
	VideoDto updateCompleted(UUID id, Boolean completed);
	VideoDto updateCurrentState(UUID id, Integer currentState);
	VideoDto updateNote(UUID id, String note);
}

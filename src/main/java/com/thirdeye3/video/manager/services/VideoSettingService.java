package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.VideoSettingDto;
import java.util.List;

public interface VideoSettingService {
    VideoSettingDto createVideoSetting(VideoSettingDto dto);
    VideoSettingDto getVideoSettingById(Long id);
    List<VideoSettingDto> getAllVideoSettings();
    VideoSettingDto updateVideoSetting(Long id, VideoSettingDto dto);
    void deleteVideoSetting(Long id);
	VideoSettingDto updateActive(Long id);
	VideoSettingDto getActiveVideoSetting();
}

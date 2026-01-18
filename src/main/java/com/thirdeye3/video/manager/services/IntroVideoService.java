package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.IntroVideoDto;
import java.util.List;

public interface IntroVideoService {
    IntroVideoDto createIntroVideo(IntroVideoDto dto);
    IntroVideoDto getIntroVideoById(Long id);
    List<IntroVideoDto> getAllIntroVideos();
    IntroVideoDto updateIntroVideo(Long id, IntroVideoDto dto);
    void deleteIntroVideo(Long id);
    IntroVideoDto updateActive(Long id);
    IntroVideoDto getActiveIntroVideo();
}

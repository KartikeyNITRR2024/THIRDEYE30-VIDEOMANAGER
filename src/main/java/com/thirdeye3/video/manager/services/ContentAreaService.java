package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.ContentAreaDto;
import java.util.List;

public interface ContentAreaService {
    ContentAreaDto createContentArea(ContentAreaDto dto);
    ContentAreaDto getContentAreaById(Long id);
    List<ContentAreaDto> getAllContentAreas();
    ContentAreaDto updateContentArea(Long id, ContentAreaDto dto);
    void deleteContentArea(Long id);
    void activateContentArea(Long id);
    ContentAreaDto getActiveContentArea();
}

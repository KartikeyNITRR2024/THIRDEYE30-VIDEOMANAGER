package com.thirdeye3.video.manager.services;

import com.thirdeye3.video.manager.dtos.AdvertainmentDto;
import java.util.List;

public interface AdvertainmentService {
    AdvertainmentDto createAdvertainment(AdvertainmentDto dto);
    AdvertainmentDto getAdvertainmentById(Long id);
    List<AdvertainmentDto> getAllAdvertainments();
    AdvertainmentDto updateAdvertainment(Long id, AdvertainmentDto dto);
    void deleteAdvertainment(Long id);
    AdvertainmentDto updateActive(Long id);
    AdvertainmentDto getActiveAdvertainment();
}

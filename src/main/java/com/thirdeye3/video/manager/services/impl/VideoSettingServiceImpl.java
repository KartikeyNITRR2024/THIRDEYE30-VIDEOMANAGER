package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.VideoSettingDto;
import com.thirdeye3.video.manager.entities.VideoSetting;
import com.thirdeye3.video.manager.repositories.VideoSettingRepository;
import com.thirdeye3.video.manager.services.VideoSettingService;
import com.thirdeye3.video.manager.utils.Mapper; 
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoSettingServiceImpl implements VideoSettingService {

    private static final Logger logger = LoggerFactory.getLogger(VideoSettingServiceImpl.class);

    @Autowired
    private VideoSettingRepository videoSettingRepository;

    private Mapper mapper = new Mapper(); 

    @Override
    @Transactional
    public VideoSettingDto createVideoSetting(VideoSettingDto dto) {
        logger.info("Creating VideoSetting with name: {}", dto.getSettingName());
        VideoSetting setting = mapper.mapDtoToEntity(dto);
        setting.setActive(false);
        setting.setLastlyUsed(LocalDateTime.now());
        VideoSetting savedSetting = videoSettingRepository.save(setting);
        logger.info("VideoSetting created with id: {}", savedSetting.getSettingId());
        return mapper.mapToDto(savedSetting); 
    }

    @Override
    @Transactional(readOnly = true)
    public VideoSettingDto getVideoSettingById(Long id) {
        logger.info("Fetching VideoSetting with id: {}", id);
        VideoSetting setting = videoSettingRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("VideoSetting not found with id: {}", id);
                    return new ResourceNotFoundException("Video Setting not found with ID: " + id);
                });
        return mapper.mapToDto(setting);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VideoSettingDto> getAllVideoSettings() {
        logger.info("Fetching all VideoSettings ordered by active and lastlyUsed");
        List<VideoSettingDto> settings = videoSettingRepository.findAllByOrderByActiveDescLastlyUsedDesc().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        logger.info("Total VideoSettings found: {}", settings.size());
        return settings;
    }

    @Override
    @Transactional
    public VideoSettingDto updateVideoSetting(Long id, VideoSettingDto dto) {
        logger.info("Updating VideoSetting with id: {}", id);
        VideoSetting setting = videoSettingRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("VideoSetting not found with id: {}", id);
                    return new ResourceNotFoundException("Video Setting not found with ID: " + id);
                });
        dto.setActive(setting.getActive());
        setting = mapper.mapDtoToEntity(dto);
        setting.setLastlyUsed(LocalDateTime.now());
        VideoSetting updatedSetting = videoSettingRepository.save(setting);
        logger.info("VideoSetting updated with id: {}", updatedSetting.getSettingId());
        return mapper.mapToDto(updatedSetting);
    }

    @Override
    public void deleteVideoSetting(Long id) {
        logger.info("Deleting VideoSetting with id: {}", id);
        videoSettingRepository.deleteById(id);
        logger.info("VideoSetting deleted with id: {}", id);
    }

    @Override
    @Transactional
    public VideoSettingDto updateActive(Long id) {
        logger.info("Deactivating all VideoSettings");
        videoSettingRepository.deactivateAll();

        logger.info("Activating VideoSetting with id: {}", id);
        VideoSetting setting = videoSettingRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("VideoSetting not found with id: {}", id);
                    return new ResourceNotFoundException("Video Setting not found with ID: " + id);
                });

        setting.setActive(true);
        setting.setLastlyUsed(LocalDateTime.now());

        VideoSetting updatedSetting = videoSettingRepository.save(setting);
        logger.info("VideoSetting activated with id: {}", updatedSetting.getSettingId());

        return mapper.mapToDto(updatedSetting);
    }
    
    @Override
    @Transactional(readOnly = true)
    public VideoSettingDto getActiveVideoSetting() {
        VideoSetting setting = videoSettingRepository.findFirstByActiveTrue()
                .orElseThrow(() -> {
                    logger.error("No active VideoSetting found");
                    return new ResourceNotFoundException("No active Video Setting found");
                });

        return mapper.mapToDto(setting);
    }

}

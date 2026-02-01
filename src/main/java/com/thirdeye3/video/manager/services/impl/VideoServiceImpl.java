package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.VideoDto;
import com.thirdeye3.video.manager.entities.Video;
import com.thirdeye3.video.manager.repositories.VideoRepository;
import com.thirdeye3.video.manager.services.GroupService;
import com.thirdeye3.video.manager.services.VideoService;
import com.thirdeye3.video.manager.utils.Mapper;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private GroupService groupService;

    private Mapper mapper = new Mapper();

    @Override
    @Transactional
    public VideoDto createVideo(VideoDto videoDto) {
        logger.info("Creating video with name: {}", videoDto.getName());

        Video video = new Video();
        video.setName(videoDto.getName());
        video.setCurrentState(videoDto.getCurrentState());
        video.setVideoDate(videoDto.getVideoDate());
        video.setNote(videoDto.getNote());
        video.setCompleted(videoDto.getCompleted());
        
        if (videoDto.getGroupId() != null) {
            Long group = groupService.getGroupById(videoDto.getGroupId()).getId();
            video.setGroup(group);
            video.setIsGroupPresent(true);
        } else {
            video.setGroup(null);
            video.setIsGroupPresent(false);
        }
        
        if (videoDto.getAdId() != null) {
            video.setAdId(videoDto.getAdId());
            video.setIsAdPresent(true);
        } else {
            video.setAdId(null);
            video.setIsAdPresent(false);
        }
        
        Video savedVideo = videoRepository.save(video);
        
        logger.info("Video created successfully with id: {}", savedVideo.getId());

        return mapper.mapToDto(savedVideo);
    }

    @Override
    public VideoDto getVideoById(UUID id) {
        logger.info("Fetching video with id: {}", id);

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Video not found with id: {}", id);
                    return new ResourceNotFoundException("Video not found with ID: " + id);
                });

        return mapper.mapToDto(video);
    }

    @Override
    public List<VideoDto> getAllVideos() {
        logger.info("Fetching all videos");
        return videoRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", key = "#id")
    public void deleteVideo(UUID id) {
        logger.info("Deleting video with id: {}", id);
        if (!videoRepository.existsById(id)) {
             throw new ResourceNotFoundException("Video not found with ID: " + id);
        }
        videoRepository.deleteById(id);
        logger.info("Video deleted & Cache evicted for id: {}", id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", key = "#id")
    public VideoDto updateNote(UUID id, String note) {
        logger.info("Updating note for video id: {}", id);

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found with ID: " + id));

        video.setNote(note);
        Video updatedVideo = videoRepository.save(video);

        logger.info("Note updated & Cache evicted for video id: {}", id);
        return mapper.mapToDto(updatedVideo);
    }

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", key = "#id")
    public VideoDto updateCurrentState(UUID id, Integer currentState) {
        logger.info("Updating currentState={} for video id: {}", currentState, id);

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found with ID: " + id));

        video.setCurrentState(currentState);
        Video updatedVideo = videoRepository.save(video);

        logger.info("Current state updated & Cache evicted for video id: {}", id);
        return mapper.mapToDto(updatedVideo);
    }

    @Override
    @Transactional
    @CacheEvict(value = "combineddata", key = "#id")
    public VideoDto updateCompleted(UUID id, Boolean completed) {
        logger.info("Updating completed={} for video id: {}", completed, id);

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found with ID: " + id));

        video.setCompleted(completed);
        Video updatedVideo = videoRepository.save(video);

        logger.info("Completed flag updated & Cache evicted for video id: {}", id);
        return mapper.mapToDto(updatedVideo);
    }
}
package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3.video.manager.entities.Video;
import com.thirdeye3.video.manager.entities.VideoDetails;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;
import com.thirdeye3.video.manager.repositories.VideoDetailsRepository;
import com.thirdeye3.video.manager.repositories.VideoRepository;
import com.thirdeye3.video.manager.services.VideoDetailsService;
import com.thirdeye3.video.manager.utils.Mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VideoDetailsServiceImpl implements VideoDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(VideoDetailsServiceImpl.class);

    @Autowired
    private VideoDetailsRepository videoDetailsRepository;

    @Autowired
    private VideoRepository videoRepository;

    private Mapper mapper = new Mapper();

    @Override
    @Transactional
    public VideoDetailsDto createVideoDetails(VideoDetailsDto dto) {
        logger.info("Creating VideoDetails");

        VideoDetails details = mapper.mapDtoToEntity(dto);

        if (dto.getVideoId() != null) {
            logger.info("Linking VideoDetails with videoId: {}", dto.getVideoId());
            Video video = videoRepository.findById(dto.getVideoId())
                    .orElseThrow(() -> {
                        logger.error("Video not found with id: {}", dto.getVideoId());
                        return new ResourceNotFoundException("Video not found ID: " + dto.getVideoId());
                    });
            details.setVideo(video);
        }

        if (details.getActive() == null) {
            details.setActive(false);
        }

        VideoDetails savedDetails = videoDetailsRepository.save(details);
        logger.info("VideoDetails created with id: {}", savedDetails.getId());

        return mapper.mapToDto(savedDetails);
    }

    @Override
    public VideoDetailsDto getVideoDetailsById(Long id) {
        logger.info("Fetching VideoDetails with id: {}", id);

        VideoDetails details = videoDetailsRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("VideoDetails not found with id: {}", id);
                    return new ResourceNotFoundException("Video Details not found ID: " + id);
                });

        return mapper.mapToDto(details);
    }

    @Override
    public List<VideoDetailsDto> getAllVideoDetails() {
        logger.info("Fetching all VideoDetails ordered by active and lastlyUsed");

        List<VideoDetailsDto> list = videoDetailsRepository.findAllByOrderByActiveDescLastlyUsedDesc().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());

        logger.info("Total VideoDetails found: {}", list.size());
        return list;
    }

    @Override
    @Transactional
    public VideoDetailsDto updateVideoDetails(Long id, VideoDetailsDto dto) {
        logger.info("Updating VideoDetails with id: {}", id);

        VideoDetails details = videoDetailsRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("VideoDetails not found with id: {}", id);
                    return new ResourceNotFoundException("Video Details not found ID: " + id);
                });

        details.setIntroHeader(dto.getIntroHeader());
        details.setIntroSubHeader(dto.getIntroSubHeader());
        details.setOutroHeader(dto.getOutroHeader());
        details.setOutroSubHeader(dto.getOutroSubHeader());

        if (dto.getVideoId() != null) {
            logger.info("Updating video link for VideoDetails id: {}", id);
            Video video = videoRepository.findById(dto.getVideoId())
                    .orElseThrow(() -> {
                        logger.error("Video not found with id: {}", dto.getVideoId());
                        return new ResourceNotFoundException("Video not found ID: " + dto.getVideoId());
                    });
            details.setVideo(video);
        } else {
            details.setVideo(null);
        }

        VideoDetails updatedDetails = videoDetailsRepository.save(details);
        logger.info("VideoDetails updated with id: {}", updatedDetails.getId());

        return mapper.mapToDto(updatedDetails);
    }

    @Override
    public void deleteVideoDetails(Long id) {
        logger.info("Deleting VideoDetails with id: {}", id);
        videoDetailsRepository.deleteById(id);
        logger.info("VideoDetails deleted with id: {}", id);
    }

    @Override
    @Transactional
    public VideoDetailsDto updateActive(Long id) {
        logger.info("Deactivating all VideoDetails");
        videoDetailsRepository.deactivateAll();

        logger.info("Activating VideoDetails with id: {}", id);
        VideoDetails details = videoDetailsRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("VideoDetails not found with id: {}", id);
                    return new ResourceNotFoundException("Video Details not found ID: " + id);
                });

        details.setActive(true);
        details.setLastlyUsed(LocalDateTime.now());

        VideoDetails updatedDetails = videoDetailsRepository.save(details);
        logger.info("VideoDetails activated with id: {}", updatedDetails.getId());

        return mapper.mapToDto(updatedDetails);
    }

    @Override
    public VideoDetailsDto getActiveVideoDetails() {
        logger.info("Fetching active VideoDetails");

        VideoDetails details = videoDetailsRepository.findFirstByActiveTrue()
                .orElseThrow(() -> {
                    logger.error("No active VideoDetails found");
                    return new ResourceNotFoundException("No active Video Details found");
                });

        return mapper.mapToDto(details);
    }

    @Override
    public VideoDetailsDto getVideoDetailsByVideoId(UUID videoId) {
        logger.info("Fetching single VideoDetail by videoId: {}", videoId);

        return videoDetailsRepository.findFirstByVideo_Id(videoId)
                .map(mapper::mapToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Video details not found for ID: " + videoId));
    }
}


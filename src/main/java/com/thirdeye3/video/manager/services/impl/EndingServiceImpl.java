package com.thirdeye3.video.manager.services.impl;

import com.thirdeye3.video.manager.dtos.EndingDto;
import com.thirdeye3.video.manager.entities.Ending;
import com.thirdeye3.video.manager.entities.Video;
import com.thirdeye3.video.manager.repositories.EndingRepository;
import com.thirdeye3.video.manager.repositories.VideoRepository;
import com.thirdeye3.video.manager.services.EndingService;
import com.thirdeye3.video.manager.utils.Mapper;
import com.thirdeye3.video.manager.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EndingServiceImpl implements EndingService {

    private static final Logger logger = LoggerFactory.getLogger(EndingServiceImpl.class);

    private static final Long SINGLETON_ID = 1L;

    @Autowired
    private EndingRepository endingRepository;

    @Autowired
    private VideoRepository videoRepository;

    private Mapper mapper = new Mapper();

    @Override
    public EndingDto getEnding() {
        logger.info("Fetching Ending configuration");

        Ending ending = endingRepository.findById(SINGLETON_ID)
                .orElseGet(() -> {
                    logger.info("Ending not found. Creating new Ending with id: {}", SINGLETON_ID);
                    Ending newEnding = new Ending();
                    newEnding.setId(SINGLETON_ID);
                    return endingRepository.save(newEnding);
                });

        logger.info("Ending fetched successfully with id: {}", ending.getId());
        return mapper.mapToDto(ending);
    }

    @Override
    @Transactional
    public EndingDto updateEnding(UUID videoId) {
        logger.info("Updating Ending configuration");

        Ending ending = endingRepository.findById(SINGLETON_ID)
                .orElseGet(() -> {
                    logger.info("Ending not found. Creating new Ending with id: {}", SINGLETON_ID);
                    return new Ending(SINGLETON_ID, null);
                });

        if (videoId != null) {
            logger.info("Linking Ending with videoId: {}", videoId);
            Video video = videoRepository.findById(videoId)
                    .orElseThrow(() -> {
                        logger.error("Video not found with id: {}", videoId);
                        return new ResourceNotFoundException("Video not found with ID: " + videoId);
                    });
            ending.setVideo(video);
        } else {
            logger.info("Removing linked video from Ending");
            ending.setVideo(null);
        }

        Ending savedEnding = endingRepository.save(ending);
        logger.info("Ending updated successfully with id: {}", savedEnding.getId());

        return mapper.mapToDto(savedEnding);
    }
}

